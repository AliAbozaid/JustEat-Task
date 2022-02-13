package io.aliabozid.justeat

import android.app.Application
import io.aliabozid.justeat.restaurants.data.di.appModule
import io.aliabozid.justeat.restaurants.data.di.dataModule
import io.aliabozid.justeat.restaurants.domain.di.domainModule
import io.aliabozid.justeat.restaurants.presentation.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class JustEatApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@JustEatApp)
            modules(
                listOf(
                    appModule,
                    dataModule,
                    domainModule,
                    viewModelsModule
                )
            )
        }
    }
}
