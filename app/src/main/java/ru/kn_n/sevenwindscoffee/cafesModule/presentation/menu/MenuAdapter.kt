package ru.kn_n.sevenwindscoffee.cafesModule.presentation.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kn_n.sevenwindscoffee.R
import ru.kn_n.sevenwindscoffee.cafesModule.data.MenuResponse
import ru.kn_n.sevenwindscoffee.databaseModule.Order
import ru.kn_n.sevenwindscoffee.databinding.ItemCoffeeCardBinding
import ru.kn_n.sevenwindscoffee.utils.extensions.ZERO
import ru.kn_n.sevenwindscoffee.utils.extensions.show

class MenuAdapter(
    val addOrder: (order: List<Order>) -> Unit
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private val orderDB = mutableListOf<Order>()
    private val items = mutableListOf<MenuResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCoffeeCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(
        private val binding: ItemCoffeeCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: MenuResponse, position: Int) {
            var order = orderDB.find { it.id == data.id }
            var count = order?.count ?: Int.ZERO
            val id = orderDB.indexOf(order)

            binding.addBlock.show()
            binding.title.text = data.name
            binding.subtitle.text = "${data.price} руб"
            Glide.with(binding.root)
                .load(data.imageURL)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(binding.image)
            binding.count.text = count.toString()

            binding.minus.setOnClickListener {
                if (count > Int.ZERO) {
                    count--
                    if (count == Int.ZERO) {
                        orderDB.remove(order)
                    } else {
                        orderDB[id].count = count
                    }
                    notifyItemChanged(position)
                }
            }

            binding.plus.setOnClickListener {
                if (count == Int.ZERO) {
                    count++
                    orderDB.add(
                        Order(
                            id = data.id,
                            name = data.name,
                            count = count,
                            price = data.price
                        )
                    )
                } else {
                    count++
                    orderDB[id].count = count
                }
                notifyItemChanged(position)
            }
        }
    }

    fun setItems(data: List<MenuResponse>, dataOrder: List<Order>) {
        items.clear()
        items.addAll(data)
        orderDB.clear()
        orderDB.addAll(dataOrder)
        notifyDataSetChanged()
    }

    fun getOrder(){
        addOrder(orderDB)
    }
}