package tw.helloandroid.ui

import android.app.Application
import tw.helloandroid.extensions.DelegateExt.notNullSingleValue

class App : Application() {
    companion object {
        var instance: App by notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
