package ru.kn_n.sevenwindscoffee.utils.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.databinding.WidgetEditTextBinding
import ru.kn_n.sevenwindscoffee.utils.extensions.gone
import ru.kn_n.sevenwindscoffee.utils.extensions.show

@SuppressLint("Recycle")
class EditTextWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){
    private val binding = WidgetEditTextBinding.inflate(LayoutInflater.from(context), this)

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.EditTextWidget)

            val inputTextType = typedArray.getInt(R.styleable.EditTextWidget_typeInputText, 16)
            val hintText = typedArray.getString(R.styleable.EditTextWidget_hintText)
            val label = typedArray.getString(R.styleable.EditTextWidget_label)
            val errorText = typedArray.getString(R.styleable.EditTextWidget_errorText)

            binding.editText.apply {
                hint = hintText
                inputType = inputTextType
            }
            binding.label.text = label
            binding.error.text = errorText
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.error.visibility == VISIBLE) hideError()
            }
        })
    }

    fun getText() : String = binding.editText.text.toString()
    fun setText(s: String){
        binding.editText.text.append(s)
    }

    fun showError() = binding.error.show()
    fun hideError() = binding.error.gone()

    fun checkForEmpty(): Boolean{
        if (binding.editText.text.isEmpty()) {
            showError()
            return false
        }
        else {
            hideError()
            return true
        }
    }
}