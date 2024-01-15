package ru.kn_n.sevenwindscoffee.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.kn_n.sevenwindscoffee.utils.extensions.showAlert
import ru.kn_n.sevenwindscoffee.utils.extensions.visible
import toothpick.Toothpick
import javax.inject.Inject

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)

        setupViewModelFactory()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModelFactory() {
        viewModelFactory = Toothpick.openScope(Scopes.APP_SCOPE).getInstance(ViewModelFactory::class.java)
    }

    fun loading(
        loadingView: View,
        show: Boolean
    ) {
        loadingView.visible(show)
    }

    fun showError(
        errorTitle: String,
        errorMsg: String
    ) {
        this.showAlert(errorTitle, errorMsg)
    }
}