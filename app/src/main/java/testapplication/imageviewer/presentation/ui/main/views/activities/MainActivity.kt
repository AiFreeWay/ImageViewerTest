package testapplication.imageviewer.presentation.ui.main.views.activities

import android.arch.lifecycle.LifecycleRegistry
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import testapplication.imageviewer.R
import testapplication.imageviewer.presentation.ui.main.presenters.AcMainPresenter
import testapplication.imageviewer.presentation.ui.main.views.abstractions.MainView

class MainActivity : AppCompatActivity(), MainView {

    @BindView(R.id.ac_main_toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.ac_main_progress)
    lateinit var mProgressBar: ProgressBar

    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)
    private lateinit var mPresenter: AcMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)
        ButterKnife.bind(this)
        mPresenter = AcMainPresenter(this)
    }

    override fun getLifecycle(): LifecycleRegistry = mLifecycle

    override fun getActivity(): AppCompatActivity = this

    override fun getFragmentLayoutId(): Int = R.id.ac_main_fl_body

    override fun startProgress() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun stopProgress() {
        mProgressBar.visibility = View.GONE
    }

    override fun setToolbarTitle(title: String) {
        mToolbar.setTitle(title)
    }
}
