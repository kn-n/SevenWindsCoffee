package ru.kn_n.sevenwindscoffee.cafesModule.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.databaseModule.Order
import ru.kn_n.sevenwindscoffee.databinding.FragmentMenuBinding
import ru.kn_n.sevenwindscoffee.utils.base.BaseFragment
import javax.inject.Inject

class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {

    @Inject
    lateinit var viewModel: MenuViewModel

    private val menuAdapter by lazy {
        MenuAdapter(
            addOrder = ::addOrder
        )
    }

    private val order = mutableListOf<Order>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]

        arguments?.let {
            val id = it.getString("ID")
            id?.let {
                viewModel.alreadyRegUser.observe(viewLifecycleOwner) {user ->
                    viewModel.getMenu(id, user[0].token)
                }
            }
        }

        initButtons()
        initAdapter()
        initObservers()
    }

    private fun initAdapter() {
        binding.cafes.apply {
            adapter = menuAdapter
        }
    }

    private fun initObservers() {
        viewModel.order.observe(viewLifecycleOwner) {
            order.addAll(it)
        }
        viewModel.success.observe(viewLifecycleOwner) {
            menuAdapter.setItems(it, order)
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
        binding.toCart.setOnClickListener {
            menuAdapter.getOrder()
            findNavController().navigate(R.id.action_menuFragment_to_orderFragment)
        }
    }

    private fun addOrder(order: List<Order>) {
        viewModel.clear()
        order.forEach {
            viewModel.add(it)
        }
    }
}