package ru.kn_n.sevenwindscoffee.cafesModule.presentation.order

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kn_n.sevenwindscoffee.databaseModule.Order
import ru.kn_n.sevenwindscoffee.databinding.FragmentCafesBinding
import ru.kn_n.sevenwindscoffee.utils.base.BaseFragment
import javax.inject.Inject

class OrderFragment : BaseFragment<FragmentCafesBinding>(FragmentCafesBinding::inflate) {

    @Inject
    lateinit var viewModel: OrderViewModel

    private val orderAdapter by lazy {
        OrderAdapter(
            updateOrder = ::updateOrder,
            deleteOrder = ::deleteOrder
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[OrderViewModel::class.java]

        initAdapter()
        initObservers()
    }

    private fun initObservers() {
        viewModel.order.observe(viewLifecycleOwner){
            orderAdapter.setItems(it)
        }
    }

    private fun initAdapter() {
        binding.cafes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
    }

    private fun updateOrder(order: Order){
        viewModel.update(order)
    }

    private fun deleteOrder(order: Order){
        viewModel.delete(order)
    }
}