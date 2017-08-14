package testapplication.imageviewer.presentation.ui.main.views.fragments

import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import testapplication.imageviewer.R
import testapplication.imageviewer.presentation.ui.base.FragmentChildMainView
import testapplication.imageviewer.presentation.ui.main.views.abstractions.IImageViewerView

/**
 * Created by root on 14.08.17.
 */
class ImageViewerFragment() : FragmentChildMainView(), IImageViewerView {

    companion object {
        val FRAGMENT_KEY = "image_viewer_fragment"
    }

    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_image_viewer, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        getMainView().setToolbarTitle("awd")
    }

    override fun getLifecycle(): LifecycleRegistry = mLifecycle
}