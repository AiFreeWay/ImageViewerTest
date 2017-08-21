package testapplication.imageviewer.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import testapplication.imageviewer.R
import testapplication.imageviewer.domain.models.Image

/**
 * Created by root on 16.08.17.
 */
class ImageHolder: BaseHolder<Image> {

    private val mOnClick: (Image) -> Unit

    @BindView(R.id.fmt_image_holder_iv_image)
    lateinit var mIvImage: ImageView

    constructor(context: Context, onClick: (Image) -> Unit) : super(context) {
        mOnClick = onClick
    }

    constructor(view: View, onClick: (Image) -> Unit) : super(view) {
        ButterKnife.bind(this, itemView)
        mOnClick = onClick
    }

    override fun create(viewGroup: ViewGroup): BaseHolder<Image> {
        val view = viewInflater(viewGroup, R.layout.v_image_holder)
        return ImageHolder(view, mOnClick)
    }

    override fun bind(dataModel: Image) {
        itemView.setOnClickListener { mOnClick.invoke(dataModel) }
        Glide.with(itemView.context)
                .load(dataModel.getUri())
                .into(mIvImage)
    }
}