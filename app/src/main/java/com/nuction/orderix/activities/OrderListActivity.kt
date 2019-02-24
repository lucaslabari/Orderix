package com.nuction.orderix.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nuction.orderix.R
import com.nuction.orderix.adapters.OrderAdapter
import com.nuction.orderix.data.Order
import com.nuction.orderix.utils.Constants
import com.nuction.orderix.viewmodels.OrderViewModel
import kotlinx.android.synthetic.main.activity_order_list.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper


class OrderListActivity : AppCompatActivity(), OrderAdapter.OnItemClickListener {

    private lateinit var orderViewModel: OrderViewModel
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        // Set adapter to Recycle View
        recycler_view.layoutManager = LinearLayoutManager(this)
        orderAdapter = OrderAdapter()
        recycler_view.adapter = orderAdapter

        // ViewModel
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
        orderViewModel.getAllOrders().observe(this, Observer<List<Order>> {
            orderAdapter.setOrders(it)
        })

        // Floating Button. Add Order.
        button_add_order.setOnClickListener() {
            val intent = Intent(this@OrderListActivity, OrderDetailActivity::class.java)
            startActivityForResult(intent, Constants.INTENT_CREATE_ORDER)
        }

        // OnItemSwiped => delete the order.
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                orderViewModel.delete(orderAdapter.getOrderAt(viewHolder.adapterPosition))
                Toast.makeText(this@OrderListActivity, "Order deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recycler_view)


        // OnItemClick => open the order to edit.
        orderAdapter.setOnItemClickListener(this)

    }

    /**
     * OnItemClick => open the order to edit.
     */
    override fun onItemClick(order: Order) {
        val intent = Intent(this@OrderListActivity, OrderDetailActivity::class.java)
        intent.putExtra(Constants.INTENT_OBJECT, order)
        startActivityForResult(intent, Constants.INTENT_UPDATE_ORDER)
    }


    /**
     * Activity result callback
     **/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val order = data?.getParcelableExtra<Order>(Constants.INTENT_OBJECT)!!
            when (requestCode) {
                // Result from Create Order
                Constants.INTENT_CREATE_ORDER -> {
                    orderViewModel.insert(order)
                    Toast.makeText(this, "Order inserted", Toast.LENGTH_SHORT).show()
                }
                // Result from Update Order
                Constants.INTENT_UPDATE_ORDER -> {
                    orderViewModel.update(order)
                    Toast.makeText(this, "Order updated", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Order not saved", Toast.LENGTH_SHORT).show()
        }
    }
}
