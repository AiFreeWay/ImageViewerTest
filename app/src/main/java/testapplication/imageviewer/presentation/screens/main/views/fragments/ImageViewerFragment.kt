package testapplication.imageviewer.presentation.screens.main.views.fragments

import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import testapplication.imageviewer.R
import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.screens.base.FragmentChildMainView
import testapplication.imageviewer.presentation.screens.main.presenters.FmtImageViewerPresenter
import testapplication.imageviewer.presentation.screens.main.presenters.abstractions.IFmtImageViewerPresenter
import testapplication.imageviewer.presentation.screens.main.views.abstractions.IImageViewerView

/**
 * Created by root on 14.08.17.
 */
class ImageViewerFragment : FragmentChildMainView(), IImageViewerView {

    companion object {
        val FRAGMENT_KEY = "image_viewer_fragment"
        val IMAGE_KEY = "image"
    }

    @BindView(R.id.fmt_image_viewer_iv_image)
    lateinit var mIvImage: ImageView
    @BindView(R.id.fmt_image_viewer_fab_favorite)
    lateinit var mFabFavorite: FloatingActionButton
    @BindView(R.id.fmt_image_viewer_fab_favorit_list)
    lateinit var mFabFavoriteList: FloatingActionButton

    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)
    private val mPresenter: IFmtImageViewerPresenter  = FmtImageViewerPresenter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_image_viewer, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mFabFavoriteList.setOnClickListener { mPresenter.openFavoriteListScreen() }
        if (arguments != null)
            mPresenter.setImageFromArguments(arguments.getSerializable(IMAGE_KEY) as Image)
        mPresenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        mFabFavorite.setOnClickListener(null)
    }

    override fun getLifecycle(): LifecycleRegistry = mLifecycle

    override fun loadNewestImage(image: Image) {
        Glide.with(context)
                .load(image.getUri())
                .into(mIvImage)
        initFab(image)
    }

    override fun changeFavoriteState(image: Image) {
        initFab(image)
    }

    override fun lockButton() {
        mFabFavorite.isClickable = false
    }

    override fun unlockButton() {
        mFabFavorite.isClickable = true
    }

    private fun initFab(image: Image) {
        if (image.isFavorite())
            mFabFavorite.setImageResource(R.drawable.ic_grade)
        else
            mFabFavorite.setImageResource(R.drawable.ic_star_border)
        mFabFavorite.setOnClickListener { mPresenter.changeStateFavorite(image) }
    }
}