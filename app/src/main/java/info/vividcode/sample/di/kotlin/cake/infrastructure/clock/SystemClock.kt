package info.vividcode.sample.di.kotlin.cake.infrastructure.clock

import info.vividcode.sample.di.kotlin.cake.clock.Clock
import info.vividcode.sample.di.kotlin.cake.logger.LoggerProvider

class SystemClock(dependencies: Dependencies) : Clock {
    interface Dependencies :
            LoggerProvider

    private val logger = dependencies.logger

    override fun millis(): Long {
        val currentTimeMillis = System.currentTimeMillis()
        logger.log("current time millis : $currentTimeMillis")
        return currentTimeMillis
    }
}
