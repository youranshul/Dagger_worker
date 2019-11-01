package symbol.android.com.testapp.dagger

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import javax.inject.Inject
import javax.inject.Provider

class AppWorkerFactory @Inject constructor(
        private val workerFactories: Map<Class<out ListenableWorker>,
                @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
    ): ListenableWorker? {
        val foundEntry =
                workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        val factoryProvider = foundEntry?.value
                ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
        return factoryProvider.get().create(appContext, workerParameters)
    }
}

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}


class BisonWorker @AssistedInject constructor(
        @Assisted private val appContext: Context,
        @Assisted private val params: WorkerParameters,
        private val niData: NiData,
        private val viData: ViData
) : Worker(appContext, params) {
    override fun doWork(): Result {

        niData.setData("I am init")
        viData.setData("I am init")
        return Result.success()
    }




    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory
    companion object {
        private const val TAG = "BisonWorker"
    }
}