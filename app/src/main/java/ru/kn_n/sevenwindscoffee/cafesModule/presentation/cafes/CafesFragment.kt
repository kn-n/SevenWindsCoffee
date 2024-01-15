package ru.kn_n.sevenwindscoffee.cafesModule.presentation.cafes

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafeResponse
import ru.kn_n.sevenwindscoffee.cafesModule.data.Cafes
import ru.kn_n.sevenwindscoffee.databinding.FragmentCafesBinding
import ru.kn_n.sevenwindscoffee.utils.base.BaseFragment
import ru.kn_n.sevenwindscoffee.utils.extensions.EMPTY
import javax.inject.Inject

class CafesFragment : BaseFragment<FragmentCafesBinding>(FragmentCafesBinding::inflate) {

    @Inject
    lateinit var viewModel: CafesViewModel

    private val cafesAdapter by lazy {
        CafesAdapter(
            onItemClick = ::onItemClick
        )
    }

    private val cafes = mutableListOf<CafeResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CafesViewModel::class.java]

        initButtons()
        initAdapter()
        initObservers()
    }

    private fun initAdapter() {
        binding.cafes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cafesAdapter
        }
    }

    private fun initObservers() {
        viewModel.alreadyRegUser.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                viewModel.getCafes(it.first().token)
            }
        }
        viewModel.success.observe(viewLifecycleOwner) {
            cafes.addAll(it)
            cafesAdapter.setItems(it)
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
        binding.map.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("POINTS", Cafes(cafes))
            findNavController().navigate(R.id.action_cafesFragment_to_mapFragment, bundle)
        }
    }

    private fun onItemClick(id: String) {
        viewModel.clearOrder()
        val bundle = Bundle()
        bundle.putString("ID", id)
        findNavController().navigate(R.id.action_cafesFragment_to_menuFragment, bundle)
    }
}