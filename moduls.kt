package com.zebra.myapplication.dagger

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class Data @Inject constructor(private val prefs: SharedPreferences) {

}

class NiDataImpl @Inject constructor() : NiData {
    override fun setData(yes: String) {
        Log.i("Ansh", "$yes NiDataImpl Called ")
    }
}

class NiDataAnotherImpl @Inject constructor() : NiData {
    override fun setData(yes: String) {
        Log.i("Ansh", "$yes NiDataAnotherImpl Called")
    }
}

interface NiData {
    fun setData(yes: String)

}

@PerActivity
class SecondData @Inject constructor(private val viData: ViData, @Named("NiDataAnotherImpl")private val niData: NiData) {

    fun setData(yes: String) {
        Log.i("Ansh", "SecondData : Called $yes")
        viData.setData("hello init")
        niData.setData("Who:")
    }

}
