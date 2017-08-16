package testapplication.imageviewer.presentation.adapters.holders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by root on 15.08.17.
 */
abstract class BaseHolder<M> : RecyclerView.ViewHolder {

    companion object {

        fun viewInflater(viewGroup: ViewGroup, resLayout: Int): View {
            val layoutInflater = LayoutInflater.from(viewGroup.context)
            return layoutInflater.inflate(resLayout, viewGroup, false)
        }
    }

    constructor(context: Context) : super(View(context)) {
    }

    constructor(itemView: View) : super(itemView) {
    }

    abstract fun create(viewGroup: ViewGroup): BaseHolder<M>

    abstract fun bind(dataModel: M)
}