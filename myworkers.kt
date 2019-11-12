package com.zebra.myapplication.dagger

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import javax.inject.Named

class BisonWorker @AssistedInject constructor(
    @Assisted  val appContext: Context,
    @Assisted  val params: WorkerParameters,
    @Named("NiDataImpl")private val niData: NiData,
    private val viData: ViData
) : Worker(appContext, params) {
    override fun doWork(): Result {

        niData.setData("Who:")
        viData.setData("I am init")
        return Result.success()
    }


    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory

}