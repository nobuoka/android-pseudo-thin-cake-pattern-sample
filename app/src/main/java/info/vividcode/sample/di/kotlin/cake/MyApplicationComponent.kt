package info.vividcode.sample.di.kotlin.cake

import info.vividcode.sample.di.kotlin.cake.infrastructure.clock.SystemClockModule
import info.vividcode.sample.di.kotlin.cake.infrastructure.logger.AndroidLoggerModule
import java.util.concurrent.atomic.AtomicReference

interface MyApplicationComponent :
        SystemClockModule, AndroidLoggerModule {

    companion object {
        fun create(d: Dependencies): ObjectGraph =
                AtomicReference<ObjectGraph>().let { r -> ObjectGraph(lazy { r.get() }, d).apply { r.set(this) } }
    }

    interface Dependencies :
            ApplicationContextProvider

    class ObjectGraph(self: Lazy<ObjectGraph>, d: Dependencies) : MyApplicationComponent,
            // コンポーネントの依存を記述。
            Dependencies by d,
            // コンポーネントを形成するモジュール群を記述。
            SystemClockModule by SystemClockModule.Impl(self), SystemClockModule.Dependencies,
            AndroidLoggerModule by AndroidLoggerModule.Impl(self), AndroidLoggerModule.Dependencies {

        // サブコンポーネントがある場合はサブコンポーネントの生成処理を記述。
        fun createMainActivityComponent(activityContextProvider: ActivityContextProvider): MainActivityComponent.ObjectGraph =
                MainActivityComponent.ObjectGraph(object : MainActivityComponent.Dependencies,
                        MyApplicationComponent by this,
                        ActivityContextProvider by activityContextProvider {})

    }

}
