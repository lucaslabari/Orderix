package com.nuction.orderix.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.nuction.orderix.R
import android.widget.Toast
import com.nuction.orderix.data.Order
import com.nuction.orderix.utils.Constants
import kotlinx.android.synthetic.main.activity_order_detail.*


class OrderDetailActivity : AppCompatActivity() {

    var order: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        // If we are updating an order => load order data
        val intent = intent
        if (intent != null && intent.hasExtra(Constants.INTENT_OBJECT)) {
            val order: Order = intent.getParcelableExtra(Constants.INTENT_OBJECT)
            this.order = order
            loadOrderData(order)
        }


        // Set Title
        title = if (order != null)
            getString(R.string.order_detail_edit_order)
        else
            getString(R.string.order_detail_create_order)


    }

    /**
     * Options Menu Creation
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflate = menuInflater
        menuInflate.inflate(R.menu.menu_order_detail, menu)
        return true
    }

    /**
     * Options Menu Item Selected
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.save_order -> {
                saveOrder()
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }


    /**
     * Populates the order data into the activity fields
     */
    private fun loadOrderData(order: Order) {
        edit_text_client.setText(order.clientName)
        edit_text_comments.setText(order.comments)
        edit_text_total.setText(order.total.toString())
        when (order.salesCondition) {
            Constants.SALES_CONDITION_CASH -> radioSalesCondition.check(R.id.radCash)
            Constants.SALES_CONDITION_CC -> radioSalesCondition.check(R.id.radCc)
            Constants.SALES_CONDITION_CHECK -> radioSalesCondition.check(R.id.radCheck)
            Constants.SALES_CONDITION_OTHERS -> radioSalesCondition.check(R.id.radOther)
            else -> radioSalesCondition.check(R.id.radCash)
        }
    }


    /**
     * Returns the order object to the calling activity to be saved
     */
    private fun saveOrder() {

        if (validateFields()) {
            val id = if (order != null) order?.id else null
            val salesCondition = when (radioSalesCondition.checkedRadioButtonId) {
                R.id.radCash -> Constants.SALES_CONDITION_CASH
                R.id.radCc -> Constants.SALES_CONDITION_CC
                R.id.radCheck -> Constants.SALES_CONDITION_CHECK
                R.id.radOther -> Constants.SALES_CONDITION_OTHERS
                else -> Constants.SALES_CONDITION_CASH
            }
            val order = Order(
                id,
                edit_text_client.text.toString(),
                salesCondition,
                edit_text_comments.text.toString(),
                edit_text_total.text.toString().toFloat()
            )

            val intent = Intent()
            intent.putExtra(Constants.INTENT_OBJECT, order)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    /**
     * Back Action called
     */
    override fun onBackPressed() {
        // TODO: Add a dialog asking to save if something changed
        //Toast.makeText(this, "Order closed", Toast.LENGTH_SHORT).show()
        super.onBackPressed()
        finish()
    }


    /**
     * Validates that all the information needed has been inserted
     */
    private fun validateFields(): Boolean {
        if (edit_text_client.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please insert client name ", Toast.LENGTH_SHORT).show()
            return false
        }
        if (edit_text_comments.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please insert a comment ", Toast.LENGTH_SHORT).show()
            return false
        }
        if (edit_text_total.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please insert the total ", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}

