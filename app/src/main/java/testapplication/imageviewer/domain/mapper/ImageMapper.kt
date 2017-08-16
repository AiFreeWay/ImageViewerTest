package testapplication.imageviewer.domain.mapper

import okhttp3.ResponseBody
import testapplication.imageviewer.data.db.entities.ImageDB
import testapplication.imageviewer.domain.models.Image

/**
 * Created by root on 15.08.17.
 */
class ImageMapper {

    companion object {

        fun mapImageFromNetwork(response: ResponseBody): Image {
            val uri = response.string().split("<div class=\"photos\">")[1]
                    .split("src=\"")[1]
                    .split("\"")[0]
                    .split("?")[0]
            return Image(uri, false)
        }

        fun mapImagesFromDB(images: List<ImageDB>): List<Image> {
            val mappedImages = ArrayList<Image>()
            images.forEach {
                mappedImages.add(Image(it.getUri(), true))
            }
            return mappedImages
        }
    }
}