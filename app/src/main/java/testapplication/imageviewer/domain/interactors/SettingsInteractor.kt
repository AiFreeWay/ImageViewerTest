package testapplication.imageviewer.domain.interactors

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import testapplication.imageviewer.domain.interactors.absctractions.ISettingsInteractor
import javax.inject.Inject

/**
 * Created by root on 21.08.17.
 */

class SettingsInteractor @Inject constructor(private val mRepository: IRepository) : ISettingsInteractor {


    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created INTERACTOR SettingsInteractor")
    }

    override fun setPushState(state: Boolean): Observable<Boolean> = mRepository.setPushState(state)

    override fun isPushOn(): Observable<Boolean> =
            mRepository.isPushOn()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}