package info.vividcode.sample.di.kotlin.cake.infrastructure.logger

import android.util.Log
import info.vividcode.sample.di.kotlin.cake.R
import info.vividcode.sample.di.kotlin.cake.ApplicationContextProvider
import info.vividcode.sample.di.kotlin.cake.logger.Logger

class AndroidLogger(private val dependencies: Dependencies) : Logger {
    interface Dependencies :
            ApplicationContextProvider

    override fun log(message: String) {
        val tagForLog = dependencies.applicationContext.getString(R.string.app_name)
        Log.d(tagForLog, message)
    }
}
