package testapplication.imageviewer.presentation.screens.main.views.activities

import android.arch.lifecycle.LifecycleRegistry
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SwitchCompat
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import testapplication.imageviewer.R
import testapplication.imageviewer.application.di.components.ParentScreenComponent
import testapplication.imageviewer.presentation.screens.main.presenters.AcMainPresenter
import testapplication.imageviewer.presentation.screens.main.views.abstractions.MainView
import android.view.MenuItem
import android.widget.TextView
import testapplication.imageviewer.presentation.screens.main.presenters.abstractions.IAcMainPresenter

class MainActivity : AppCompatActivity(), MainView {

    @BindView(R.id.ac_main_tv_toolbar)
    lateinit var mTvToolbar: TextView
    @BindView(R.id.ac_main_swt_push_on)
    lateinit var mSwtPushOn: SwitchCompat
    @BindView(R.id.ac_main_progress)
    lateinit var mProgressBar: ProgressBar

    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)
    private val mPresenter: IAcMainPresenter = AcMainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main)
        ButterKnife.bind(this)
        mPresenter.attachView(this)
        mSwtPushOn.setOnCheckedChangeListener { compoundButton, b -> mPresenter.changePushOnState(b) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            mPresenter.navigateBack()
            return true
        }
        return super.onOptionsItemSelected(item)
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

    override fun setToolbarTitle(title: Int) {
        mTvToolbar.setText(title)
    }

    override fun enableHomeToolbarButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun disableHomeToolbarButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
    }

    override fun getParentComponent(): ParentScreenComponent = mPresenter.getParentComponent()

    override fun setPushOnState(state: Boolean) {
        mSwtPushOn.isChecked = state
    }
}
