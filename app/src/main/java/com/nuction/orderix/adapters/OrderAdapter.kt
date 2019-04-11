package com.nuction.orderix.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.nuction.orderix.data.Order
import com.nuction.orderix.R
import kotlinx.android.synthetic.main.list_item_order.view.*


class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderHolder>(), Filterable {


    private var orders: List<Order> = ArrayList()
    private var filteredOrdersList: List<Order> = arrayListOf()
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_order, parent, false)
        return OrderHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(filteredOrdersList[position], listener)
    }

    override fun getItemCount(): Int = filteredOrdersList.size

    fun getOrderAt(position: Int): Order {
        return orders[position]
    }

    fun setOrders(orders: List<Order>) {
        this.orders = orders
        this.filteredOrdersList = orders
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                filteredOrdersList = if (charString.isEmpty()) {
                    orders
                } else {
                    val filteredList = arrayListOf<Order>()
                    for (row in orders) {
                        if (row.clientName.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = filteredOrdersList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredOrdersList = p1?.values as List<Order>
                notifyDataSetChanged()
            }

        }
    }


    class OrderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(order: Order, listener: OnItemClickListener) {
            itemView.text_view_client.text = order.clientName
            itemView.text_view_comments.text = order.comments
            itemView.text_view_total.text = order.total.toString()

            itemView.setOnClickListener {
                listener.onItemClick(order)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(order: Order)
    }
}
