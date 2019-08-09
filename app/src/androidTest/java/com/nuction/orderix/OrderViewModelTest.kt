package com.nuction.orderix

//import androidx.room.Room
//import androidx.test.platform.app.InstrumentationRegistry
//import com.nuction.orderix.data.AppDatabase
//import com.nuction.orderix.data.Order
//import com.nuction.orderix.data.OrderRepository
//import com.nuction.orderix.data.OrderRepositoryImpl
//import com.nuction.orderix.di.appModule
//import com.nuction.orderix.viewmodels.OrderViewModel
//import org.junit.After
//import org.junit.Assert
//import org.junit.Before
//
//import org.koin.android.ext.koin.androidApplication
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.dsl.koinApplication
//import org.koin.dsl.module
//
//import org.koin.test.KoinTest
//import org.koin.test.check.checkModules
//import org.koin.test.inject
import org.junit.Test
import org.koin.test.AutoCloseKoinTest


class OrderViewModelTest: AutoCloseKoinTest() {

 //   val orderViewModel: OrderViewModel by inject()

 //   val appContext = InstrumentationRegistry.getInstrumentation().context

//    @Before
//    fun setup() {
//        //MockitoAnnotations.initMocks(this)
//         //startKoin { modules(appModule) }.checkModules()
//
//        startKoin{
//            modules(
//                module {
//                    single {
//                        synchronized(AppDatabase::class) {
//                            Room.databaseBuilder(appContext, AppDatabase::class.java, "orderix.db")
//                                .allowMainThreadQueries()
//                                .fallbackToDestructiveMigration()
//                                .build()
//                        }
//                    }
//
//                    single { get<AppDatabase>().orderDao() }
//                    single<OrderRepository> { OrderRepositoryImpl(get()) }
//                    single { OrderViewModel(get()) }
//                })
//        }
//
//    }
//
//    @After
//    fun after() {
//        stopKoin()
//    }


//    @Test
//    fun checkModules() {
//        // use koinApplication instead of startKoin, to avoid having to close Koin after each test
//        koinApplication { modules(appModule) }.checkModules()
//
//    }


    @Test
    fun testInsertOrderOK() {
//        declareMock<OrderViewModel> {
//            // do your given behavior here
//            given(orderViewModel.deleteAllOrders()).willReturn("Hello mock")
//        }
//        orderViewModel.deleteAllOrders()
//        val order = Order(1, "clientTest", 3, "this is a comment", 354.15F)
//
//        orderViewModel.insert(order)
//        Assert.assertEquals(orderViewModel.getAllOrders().value?.size, 1)
//        orderViewModel.delete(order)
//        orderViewModel.deleteAllOrders()
//
//        Assert.assertEquals(orderViewModel.getAllOrders().value?.size, 0)


    }

}