package ilgulee.com.asurionvetcliniccodetest.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ilgulee.com.asurionvetcliniccodetest.model.Pet
import ilgulee.com.asurionvetcliniccodetest.network.DownloadStatus
import ilgulee.com.asurionvetcliniccodetest.network.NetworkModule

class PetRepository : NetworkModule.OnDownloadComplete, PetJsonParse.OnDataAvailable {
    private val petNetworkModule = NetworkModule(this)
    private var _petList = MutableLiveData<List<Pet>>()
    val petList: LiveData<List<Pet>>
        get() = _petList

    init {
        val url = createUrl(
            "https://api.myjson.com/bins/196ffs"
        )
        petNetworkModule.execute(url)
    }

    private fun createUrl(baseUrl: String): String {

        val uri = Uri.parse(baseUrl).buildUpon().build()
        Log.i("uri", uri.toString())
        return uri.toString()
    }

    override fun onDownloadCompleted(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            val petJsonParse = PetJsonParse(this)
            petJsonParse.execute(data)
        } else {
            Log.d("petrepository", "status is $status")
        }
    }

    override fun onDataAvailable(data: List<Pet>) {
        Log.d("onDataAvailable", "data is $data")
        _petList.value = data
        Log.d("onDataAvailable", "petList is $petList")
    }

    override fun onError(exception: Exception) {
        Log.d("onError", "data is $exception")
    }
}