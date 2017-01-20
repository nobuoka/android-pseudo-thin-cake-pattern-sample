package info.vividcode.sample.di.kotlin.cake

import info.vividcode.sample.di.kotlin.cake.clock.ClockProvider
import info.vividcode.sample.di.kotlin.cake.logger.LoggerProvider

interface MainActivityComponent :
        MainActivityViewModel.Provider,
        MainActivityPresenter.Provider {

    private interface InternalDependencies :
            MainActivityPresenter.Dependencies

    interface Dependencies :
            ActivityContextProvider,
            ClockProvider,
            LoggerProvider

    class ObjectGraph(dependencies: Dependencies) :
            MainActivityComponent, InternalDependencies,
            Dependencies by dependencies {
        override val mainActivityPresenter: MainActivityPresenter by lazy { MainActivityPresenter(this) }
        override val mainActivityViewModel: MainActivityViewModel by lazy { MainActivityViewModel() }
    }

}
