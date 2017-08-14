package testapplication.imageviewer.data.network

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger

/**
 * Created by root on 03.08.17.
 */
class NetworkController {

    companion object {
        const val SEASON_URI: String = "season-"
        const val API_URL: String = "http://sp.freehat.cc/episode/"
    }

    private var mApiController: ApiController

    init {
        if (BuildConfig.DEBUG) Logger.testLog("create Controller NetworkClient")

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            addLogInterceptor(httpClientBuilder)


        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClientBuilder.build())
                .build()
        mApiController = retrofit.create(ApiController::class.java)
    }

    fun getSeasons(): Observable<ResponseBody> {
        return  mApiController.getSeasons()
    }

    fun getSeasonSeries(seasonId: String): Observable<ResponseBody> {
        return  mApiController.getSeasonSeries(seasonId)
    }

    fun getSeriesDetail(seriesId: String): Observable<ResponseBody> {
        return  mApiController.getSeriesDetail(seriesId)
    }

    private fun addLogInterceptor(httpClientBuilder: OkHttpClient.Builder) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(logging)
    }
}