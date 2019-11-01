package symbol.android.com.testapp.dagger

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class Data @Inject constructor(private val prefs: SharedPreferences) {

}

class ViData @Inject constructor(private val data: Data/*, private val niData: NiData*/) {
    fun setData(yes: String) {
        Log.i("Ansh","ViData Called $yes")
    }
}

class NiData {
    fun setData(yes: String) {
        Log.i("Ansh","NiData Called $yes")
    }
}

class Presenter @Inject constructor(private val context: Context) {

    fun startWorker() {

    }
}


/*
class CustomWorker(
        context: Context, workerParameters: WorkerParameters,
                   private val data: Data, niData: NiData, viData: ViData) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}*/
