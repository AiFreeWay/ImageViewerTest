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
import javax.inject.Inject

/**
 * Created by root on 03.08.17.
 */
class NetworkController {

    companion object {
        const val API_URL: String = "https://www.pexels.com/search/forest/"
    }

    private var mApiController: ApiController

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created CONTROLLER NetworkClient")

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

    fun getNewestImage(): Observable<ResponseBody> {
        return  mApiController.getNewestImage()
    }

    private fun addLogInterceptor(httpClientBuilder: OkHttpClient.Builder) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(logging)
    }
}