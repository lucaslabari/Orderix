package com.nuction.orderix.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.nuction.orderix.R
import com.nuction.orderix.adapters.OrderAdapter
import com.nuction.orderix.data.Order
import com.nuction.orderix.utils.Constants
import com.nuction.orderix.viewmodels.OrderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), OrderAdapter.OnItemClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    // Koin provides OrderViewModel dependency
    private val orderViewModel: OrderViewModel by viewModel()
    private lateinit var searchView: SearchView
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Floating Button. Add Order.
        val fab = findViewById<FloatingActionButton>(R.id.button_add_order)
        fab.setOnClickListener {
            resetSearchView()
            val intent = Intent(this@MainActivity, OrderDetailActivity::class.java)
            startActivityForResult(intent, Constants.INTENT_CREATE_ORDER)
        }

        // Set adapter to Recycle View
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        orderAdapter = OrderAdapter()
        recyclerView.adapter = orderAdapter

        // ViewModel and LiveData
        orderViewModel.getAllOrders().observe(this, Observer<List<Order>> {
            orderAdapter.setOrders(it)
        })

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)


        // OnItemSwiped => delete the order.
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                orderViewModel.delete(orderAdapter.getOrderAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Order deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)


        // OnItemClick => open the order to edit.
        orderAdapter.setOnItemClickListener(this)

    }

    /**
     * On Create Options Menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_order_list, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.search_order)
            ?.actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                orderAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                orderAdapter.filter.filter(newText)
                return false
            }

        })
        return true
    }

    /**
     * Handles a navigation drawer item click. It detects which item was
     * clicked and displays a toast message showing which item.
     * @param item  Item in the navigation drawer
     * @return      Returns true after closing the nav drawer
     */
    @Override
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        // Handle navigation view item clicks here.
        return when (item.getItemId()) {
            R.id.nav_orders -> {
                // Handle the show orders list action.
                drawer.closeDrawer(GravityCompat.START)
                displayToast(getString(R.string.navigation_drawer_item_orders_list))
                true
            }
            else -> false
        }
    }

    /**
     * OnItemClick => open the order to edit.
     */
    override fun onItemClick(order: Order) {
        resetSearchView()
        val intent = Intent(this@MainActivity, OrderDetailActivity::class.java)
        intent.putExtra(Constants.INTENT_OBJECT, order)
        startActivityForResult(intent, Constants.INTENT_UPDATE_ORDER)
    }


    /**
     * Activity result callback
     **/
    @SuppressLint("MissingSuperCall")
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

    /**
     * Reset the Search View
     */
    private fun resetSearchView() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
    }

    /**
     * Handles the Back button: closes the nav drawer.
     */
    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            resetSearchView()
            super.onBackPressed()
        }
    }

    /**
     * Displays a toast message.
     * @param message   Message to display in toast
     */
    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
