package symbol.android.com.testapp.dagger

import com.zebra.myapplication.dagger.AppComponent
import com.zebra.myapplication.dagger.DaggerActivity
import dagger.Component
import dagger.Module
import javax.inject.Scope


@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Module
class ActivityModule {

   /* @PerActivity
    @Provides
    fun provideData(data: Data): Data = data

    @Provides
    @PerActivity
    fun provideViData(data: ViData): ViData = data*/

    /*@Provides
    @PerActivity
    fun provideNiData(): NiData{
        return NiData()
    }
*/
}

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: DaggerActivity)
}