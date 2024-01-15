package ru.kn_n.sevenwindscoffee.cafesModule.presentation.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafeResponse
import ru.kn_n.sevenwindscoffee.cafesModule.data.MenuResponse
import ru.kn_n.sevenwindscoffee.databaseModule.Order
import ru.kn_n.sevenwindscoffee.databinding.ItemCardBinding
import ru.kn_n.sevenwindscoffee.utils.Coffee
import ru.kn_n.sevenwindscoffee.utils.OrderCache
import ru.kn_n.sevenwindscoffee.utils.extensions.ZERO
import ru.kn_n.sevenwindscoffee.utils.extensions.gone
import ru.kn_n.sevenwindscoffee.utils.extensions.show
import javax.inject.Inject

class OrderAdapter (
    val updateOrder: (order: Order) -> Unit,
    val deleteOrder: (order: Order) -> Unit
): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {



    private val items = mutableListOf<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(
        private val binding: ItemCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Order, position: Int){
            binding.addBlock.show()
            binding.title.text = data.name
            binding.subtitle.text = "${data.price * data.count} руб"
            binding.count.text = data.count.toString()

            binding.minus.setOnClickListener {
                if (data.count > Int.ZERO) {
                    data.count--
                    if (data.count == Int.ZERO) {
                        deleteOrder(data)
                        notifyDataSetChanged()
                    } else {
                        updateOrder(data)
                        notifyItemChanged(position)
                    }
                }
            }

            binding.plus.setOnClickListener {
                data.count++
                updateOrder(data)
                notifyItemChanged(position)
            }
        }
    }

    fun setItems(data: List<Order>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}