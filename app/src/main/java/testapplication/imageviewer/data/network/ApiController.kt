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
    fun getSeasons(): Observable<ResponseBody>

    @GET(NetworkController.SEASON_URI+"{seasonId}")
    fun getSeasonSeries(@Path("seasonId") seasonId: String): Observable<ResponseBody>

    @GET("{seriesId}")
    fun getSeriesDetail(@Path("seriesId") seriesId: String): Observable<ResponseBody>
}