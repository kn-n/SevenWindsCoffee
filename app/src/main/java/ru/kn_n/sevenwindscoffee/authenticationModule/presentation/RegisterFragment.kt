package ru.kn_n.sevenwindscoffee.authenticationModule.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.databinding.FragmentRegisterBinding
import ru.kn_n.sevenwindscoffee.utils.base.BaseFragment
import javax.inject.Inject

class RegisterFragment: BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    @Inject
    lateinit var viewModel: RegisterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]

        initButtons()
        initObservers()
    }

    private fun initObservers() {
        viewModel.alreadyRegUser.observe(viewLifecycleOwner){
            if (it.isNotEmpty()) findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        viewModel.success.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_registerFragment_to_cafesFragment)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            showError(it.first.toString(), it.second)
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            showError("Exception", it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            loading(binding.loading, it)
        }
    }

    private fun initButtons() {
        binding.registration.setOnClickListener {
            val mail = binding.mail.checkForEmpty()
            val password = binding.password.checkForEmpty()
            val passwordRepeat = binding.passwordRepeat.checkForEmpty()
            if (mail && password && passwordRepeat){
                if (binding.password.getText() == binding.passwordRepeat.getText()){
                    viewModel.register(binding.mail.getText(), binding.password.getText())
                } else {
                    binding.passwordRepeat.showError()
                }
            }
        }
        binding.toLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}