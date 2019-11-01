package com.zebra.myapplication.dagger


import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.zebra.myapplication.R
import symbol.android.com.testapp.dagger.DaggerActivityComponent
import symbol.android.com.testapp.dagger.Presenter
import javax.inject.Inject


class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: Presenter

    override fun onCreate(
        @Nullable savedInstanceState: Bundle,
        @Nullable persistentState: PersistableBundle
    ) {
        initDagger()
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.dagger)
    }

    private fun initDagger() {
        DaggerActivityComponent.builder().appComponent(
            (getApplicationContext() as CustomApplication).component
        ).build().inject(this)
    }

    fun startWorker(view: View) {
        presenter!!.startWorker()
    }
}
