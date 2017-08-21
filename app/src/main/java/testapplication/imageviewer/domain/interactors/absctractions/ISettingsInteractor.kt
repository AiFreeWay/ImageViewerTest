package testapplication.imageviewer.domain.interactors.absctractions

import io.reactivex.Observable

/**
 * Created by root on 21.08.17.
 */
interface ISettingsInteractor {

    fun setPushState(state: Boolean): Observable<Boolean>
    fun isPushOn(): Observable<Boolean>
}