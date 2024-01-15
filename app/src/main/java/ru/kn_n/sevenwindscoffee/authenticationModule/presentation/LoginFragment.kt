package ru.kn_n.sevenwindscoffee.authenticationModule.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.databinding.FragmentLoginBinding
import ru.kn_n.sevenwindscoffee.utils.base.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        initButtons()
        initObservers()
    }

    private fun initObservers() {
        viewModel.alreadyRegUser.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val user = it[0]
                binding.mail.setText(user.email)
            }
        }
        viewModel.success.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_loginFragment_to_cafesFragment)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            showError(it.first.toString(), it.second)
            binding.mail.showError()
            binding.password.showError()
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            showError("Exception", it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            loading(binding.loading, it)
        }
    }

    private fun initButtons() {
        binding.logIn.setOnClickListener {
            val mail = binding.mail.checkForEmpty()
            val password = binding.password.checkForEmpty()
            if (mail && password) {
                viewModel.login(binding.mail.getText(), binding.password.getText())
            }
        }

        binding.toReg.setOnClickListener {
            lifecycleScope.launch {
                viewModel.toReg()
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }
}