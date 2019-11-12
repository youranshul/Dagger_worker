package com.zebra.myapplication.dagger


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.zebra.myapplication.R
import javax.inject.Inject


class DaggerActivity : AppCompatActivity(), ViewHolder {
    override fun onFinish() {

        findViewById<Button>(R.id.button).visibility = View.VISIBLE
    }

    @Inject
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dagger)

        presenter.setView(this)
    }

    private fun initDagger() {
        ((application as CustomApplication).component).plus().create().inject(this)
    }

    fun startWorker(view: View) {
        presenter.startWorker()
    }

    fun startSecondWorker(view: View) {
        startActivity(Intent(this, AnotherActivity::class.java))
        finish()
    }
}

class AnotherActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: SecondPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.another)
    }

    private fun initDagger() {
        (application as CustomApplication).component.second().create().inject(this)
    }

    fun anotherWorker(view: View) {
        presenter.startWorker()
    }

}

interface ViewHolder {

    fun onFinish()
}

class SecondPresenter @Inject constructor(
    private val context: Context,
    private val secondData: SecondData
) {

    private lateinit var viewHolder: ViewHolder

    fun startWorker() {
        secondData.setData("calling from presenter")
        /* val req = OneTimeWorkRequest.Builder(BisonWorker::class.java).build()
         WorkManager.getInstance(context).enqueue(req)

         WorkManager.getInstance(context).getWorkInfoByIdLiveData(req.id).observeForever(Observer {
             when (it.state) {
                 WorkInfo.State.SUCCEEDED -> viewHolder.onFinish()
             }
         })*/
    }

    fun setView(viewholder: ViewHolder) {
        this.viewHolder = viewholder
    }
}


class Presenter @Inject constructor(private val context: Context) {

    private lateinit var viewHolder: ViewHolder

    fun startWorker() {
        val req = OneTimeWorkRequest.Builder(BisonWorker::class.java).build()
        WorkManager.getInstance(context).enqueue(req)

        WorkManager.getInstance(context).getWorkInfoByIdLiveData(req.id).observeForever(Observer {
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> viewHolder.onFinish()
            }
        })
    }

    fun setView(viewholder: ViewHolder) {
        this.viewHolder = viewholder
    }
}