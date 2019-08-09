package com.nuction.orderix

import com.nuction.orderix.data.Order
import com.nuction.orderix.data.OrderRepository
import com.nuction.orderix.viewmodels.OrderViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class OrderViewModelUnitTest {

    @Mock
    private lateinit var orderRepository: OrderRepository


    private lateinit var orderViewModel: OrderViewModel

    @Before
    fun setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // Get a reference to the class under test
        orderViewModel = OrderViewModel(orderRepository)
    }

    @Test
    fun addition_isCorrect() {

        // Mock API response
//        Mockito.`when`(this.orderRepository.getAllOrders()).thenAnswer {
//            return
//        }
//
//        var anOrder: Order = Order(1,"cliente", 1, "comentarios", 333f)
//        orderViewModel.insert(anOrder)

        var orders: List<Order>? = orderViewModel.getAllOrders().getValue()

        assertEquals(orders?.size, 1)
    }
}
