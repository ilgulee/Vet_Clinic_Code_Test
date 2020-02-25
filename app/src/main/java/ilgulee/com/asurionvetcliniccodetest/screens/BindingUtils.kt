package ilgulee.com.asurionvetcliniccodetest.screens

import android.app.Activity
import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ilgulee.com.asurionvetcliniccodetest.model.Pet
import okhttp3.*
import java.io.IOException

@BindingAdapter("petImage")
fun ImageView.setPetImage(item: Pet) {
    val activity = context as Activity
    val imageDownload = ImageDownload(this, item.imageUrl, activity)
    imageDownload.loadImageFromUrl()
}

@BindingAdapter("petTitle")
fun TextView.setPetTitle(item: Pet) {
    text = item.title
}

class ImageDownload(private val imageView: ImageView, val url: String, val activity: Activity) {
    private val client by lazy {
        OkHttpClient()
    }
    private val request by lazy {
        Request.Builder().url(url).build()
    }

    fun loadImageFromUrl() {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val inputStream = response.body?.byteStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                activity.runOnUiThread {
                    imageView.setImageBitmap(bitmap)
                }

            }
        })
    }

}