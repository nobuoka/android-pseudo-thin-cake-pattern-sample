package info.vividcode.sample.di.kotlin.cake.infrastructure.clock

import info.vividcode.sample.di.kotlin.cake.clock.Clock
import info.vividcode.sample.di.kotlin.cake.clock.ClockProvider

interface SystemClockModule : ClockProvider {

    interface Dependencies :
            SystemClock.Dependencies

    class Impl(r: Lazy<Dependencies>) : SystemClockModule {
        override val clock: Clock by lazy { SystemClock(r.value) }
    }

}
