package io.aliabozid.justeat.restaurants.base

import io.aliabozid.justeat.assets.utils.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class DispatcherImplTest : Dispatcher {
    override fun main(): CoroutineDispatcher = TestCoroutineDispatcher()

    override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()

    override fun default(): CoroutineDispatcher = TestCoroutineDispatcher()
}
