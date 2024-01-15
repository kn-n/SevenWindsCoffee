package ru.kn_n.sevenwindscoffee.cafesModule.presentation.cafes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafeResponse
import ru.kn_n.sevenwindscoffee.databinding.ItemCardBinding
import ru.kn_n.sevenwindscoffee.utils.extensions.gone

class CafesAdapter(
    val onItemClick: (id: String) -> Unit
): RecyclerView.Adapter<CafesAdapter.ViewHolder>() {

    private val items = mutableListOf<CafeResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(
        private val binding: ItemCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CafeResponse){
            binding.addBlock.gone()
            binding.title.text = data.name

            binding.card.setOnClickListener {
                onItemClick(data.id.toString())
            }
        }
    }

    fun setItems(data: List<CafeResponse>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}