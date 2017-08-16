package testapplication.imageviewer.data.network

import retrofit2.http.GET
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Path

/**
 * Created by root on 03.08.17.
 */
interface ApiController {

    @GET(".")
    fun getNewestImage(): Observable<ResponseBody>
}