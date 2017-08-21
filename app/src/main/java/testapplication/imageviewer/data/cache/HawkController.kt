package testapplication.imageviewer.data.cache

import com.orhanobut.hawk.Hawk
import io.reactivex.Observable
import testapplication.imageviewer.domain.models.Image

/**
 * Created by root on 18.08.17.
 */
class HawkController {

    companion object {

        val LAST_LOADED_IMAGE_KEY = "last_loaded_image_key"
        val CAN_UPDATE_IMAGE_KEY = "can_update_image_key"
        val PUSH_ON_KEY = "push_on_key"

        fun putLastLoadedImage(image: Image): Observable<Image> {
            Hawk.put(LAST_LOADED_IMAGE_KEY, image)
            return Observable.just(image)
        }

        fun getLastLoadedImage(): Observable<Image> {
            if (Hawk.contains(LAST_LOADED_IMAGE_KEY))
                return Observable.just(Hawk.get(LAST_LOADED_IMAGE_KEY))
            return Observable.just(Image("", false))
        }

        fun accessUpdateUi(): Observable<Boolean> {
            Hawk.put(CAN_UPDATE_IMAGE_KEY, true)
            return Observable.just(true)
        }

        fun deniedUpdateUi(): Observable<Boolean> {
            Hawk.put(CAN_UPDATE_IMAGE_KEY, false)
            return Observable.just(false)
        }

        fun isCanUpdateUi(): Observable<Boolean> {
            if (Hawk.contains(CAN_UPDATE_IMAGE_KEY))
                return Observable.just(Hawk.get(CAN_UPDATE_IMAGE_KEY))
            return Observable.just(false)
        }

        fun setPushState(state: Boolean): Observable<Boolean> {
            Hawk.put(PUSH_ON_KEY, state)
            return Observable.just(state)
        }

        fun isPushOn(): Observable<Boolean> {
            if (Hawk.contains(PUSH_ON_KEY))
                return Observable.just(Hawk.get(PUSH_ON_KEY))
            return Observable.just(true)
        }
    }
}