package com.zebra.myapplication.dagger

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope


@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Module()
abstract class ActivityModule {

    @Binds
    abstract fun getNidata(niDataImpl: NiDataImpl) : NiData


}

@PerActivity
@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: DaggerActivity)
}