package testapplication.imageviewer.presentation.screens.main.views.fragments

import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import testapplication.imageviewer.R
import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.adapters.MultyRvAdapter
import testapplication.imageviewer.presentation.adapters.holders.ImageHolder
import testapplication.imageviewer.presentation.screens.base.FragmentChildMainView
import testapplication.imageviewer.presentation.screens.main.presenters.FmtFavoriteImagesPresenter
import testapplication.imageviewer.presentation.screens.main.presenters.abstractions.IFmtFavoriteImagesPresenter
import testapplication.imageviewer.presentation.screens.main.views.abstractions.IFavoriteImagesView

/**
 * Created by root on 16.08.17.
 */
class FavoriteImagesFragment : FragmentChildMainView(), IFavoriteImagesView {

    companion object {
        val FRAGMENT_KEY = "favorite_images_fragment"
    }

    @BindView(R.id.fmt_favorite_images_rv_images)
    lateinit var mRvImages: RecyclerView

    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)
    private val mPresenter: IFmtFavoriteImagesPresenter = FmtFavoriteImagesPresenter()
    private lateinit var mAdapter: MultyRvAdapter<Image>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fmt_favorite_images, container, false)
        ButterKnife.bind(this, view);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAdapter = MultyRvAdapter(ImageHolder(context, mPresenter::showSavedImageScreen))
        mRvImages.adapter = mAdapter
        mRvImages.layoutManager = LinearLayoutManager(context)
        mPresenter.attachView(this)
    }

    override fun loadImages(images: List<Image>) {
        mAdapter.loadData(images)
    }

    override fun getLifecycle(): LifecycleRegistry = mLifecycle
}