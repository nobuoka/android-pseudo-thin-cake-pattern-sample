package info.vividcode.sample.di.kotlin.cake

import android.databinding.ObservableField

class MainActivityViewModel {
    interface Provider {
        val mainActivityViewModel: MainActivityViewModel
    }

    val currentTime: ObservableField<String> = ObservableField()

}
