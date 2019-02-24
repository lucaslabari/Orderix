package com.nuction.orderix.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nuction.orderix.data.Order
import com.nuction.orderix.R
import kotlinx.android.synthetic.main.list_item_order.view.*


class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderHolder>() {
    private var orders: List<Order> = ArrayList()
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_order, parent, false)
        return OrderHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(orders[position], listener)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    fun getOrderAt(position: Int): Order {
        return orders.get(position)
    }

    fun setOrders(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
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
