package info.vividcode.sample.di.kotlin.cake

import info.vividcode.sample.di.kotlin.cake.clock.ClockProvider
import info.vividcode.sample.di.kotlin.cake.logger.LoggerProvider

class MainActivityPresenter(d: Dependencies) {
    interface Dependencies :
            ClockProvider,
            LoggerProvider,
            MainActivityViewModel.Provider

    interface Provider {
        val mainActivityPresenter: MainActivityPresenter
    }

    private var thread: Thread? = null
    private val clock = d.clock
    private val logger = d.logger
    private val mainActivityViewModel = d.mainActivityViewModel

    fun onStart() {
        thread = Thread {
            while (true) {
                mainActivityViewModel.currentTime.set(clock.millis().toString())
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    logger.log("interrupted!")
                    break
                }
            }
            logger.log("Thread loop finished!")
        }
        thread?.start()
    }

    fun onStop() {
        thread?.interrupt()
        thread = null
    }
}
