package testapplication.imageviewer.domain.models

/**
 * Created by root on 21.08.17.
 */
class Monade<T>(private val mResult: Boolean, private val mData: T) {

    fun getResult() = mResult

    fun getData() = mData
}