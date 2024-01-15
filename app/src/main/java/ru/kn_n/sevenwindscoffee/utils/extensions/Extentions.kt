package ru.kn_n.sevenwindscoffee.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.HttpException
import retrofit2.Response
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.utils.base.NetworkResult

val String.Companion.EMPTY: String
    get() = ""

val String.Companion.DASH: String
    get() = "-"

val Int.Companion.ZERO: Int
    get() = 0

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun Fragment.showAlert(
    title: String,
    msg: String
){
    MaterialAlertDialogBuilder(this.requireContext())
        .setTitle(title)
        .setMessage(msg)
        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
            dialog.dismiss()
        }
        .show()
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}