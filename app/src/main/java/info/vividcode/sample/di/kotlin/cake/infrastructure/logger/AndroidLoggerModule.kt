package info.vividcode.sample.di.kotlin.cake.infrastructure.logger

import info.vividcode.sample.di.kotlin.cake.logger.Logger
import info.vividcode.sample.di.kotlin.cake.logger.LoggerProvider

interface AndroidLoggerModule : LoggerProvider {

    interface Dependencies :
            AndroidLogger.Dependencies

    class Impl(r: Lazy<Dependencies>) : AndroidLoggerModule {
        override val logger: Logger by lazy { AndroidLogger(r.value) }
    }

}
