package com.nuction.orderix

import com.nuction.orderix.di.appModule
import com.nuction.orderix.viewmodels.OrderViewModel
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.inject

class OrderViewModelTest: KoinTest {

    val orderViewModel: OrderViewModel by inject()

    @Before
    fun before() {
        startKoin { modules(appModule) }
    }

    @After
    fun after() {
        stopKoin()
    }

//    @Test
//    fun testKoinComponents() {
//        assertEquals(orderViewModel, myApplication.helloService)
//    }

    @Test
    fun `checking modules`() {
        // use koinApplication instead of startKoin, to avoid having to close Koin after each test
        koinApplication { modules(appModule) }.checkModules()
    }



}