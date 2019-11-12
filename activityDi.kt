package com.zebra.myapplication.dagger

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import javax.inject.Named
import javax.inject.Scope


@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Module()
abstract class ActivityModule {

    @Binds
    @Named("NiDataImpl")
    abstract fun getNidata(niDataImpl: NiDataImpl) : NiData

    @Binds
    @Named("NiDataAnotherImpl")
    abstract fun provideAnotherImpl(niDataImpl: NiDataAnotherImpl) : NiData

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

@PerActivity
@Subcomponent
interface SecondActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SecondActivityComponent
    }

    fun inject(activity: AnotherActivity)
}