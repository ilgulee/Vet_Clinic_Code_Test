package ilgulee.com.asurionvetcliniccodetest.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ilgulee.com.asurionvetcliniccodetest.model.Config
import ilgulee.com.asurionvetcliniccodetest.network.DownloadStatus
import ilgulee.com.asurionvetcliniccodetest.network.NetworkModule

class ConfigRepository : NetworkModule.OnDownloadComplete, ConfigJsonParse.OnDataAvailable {
    private val configNetworkModule = NetworkModule(this)
    private var _config = MutableLiveData<Config>()
    val config: LiveData<Config>
        get() = _config

    init {
        val url = createUrl(
            "https://api.myjson.com/bins/1fv63s" //true, true
//            "https://api.myjson.com/bins/1643zk" //false, false
//            "https://api.myjson.com/bins/1cfqfk" //true, false
        )
        configNetworkModule.execute(url)
    }

    private fun createUrl(baseUrl: String): String {

        val uri = Uri.parse(baseUrl).buildUpon().build()
        Log.i("uri", uri.toString())
        return uri.toString()
    }

    override fun onDownloadCompleted(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            val configJsonParse = ConfigJsonParse(this)
            configJsonParse.execute(data)
        } else {
            Log.d("config", "status is $status")
        }
    }

    override fun onDataAvailable(data: Config) {
        Log.d("onDataAvailable", "data is $data")
        _config.value = data
        Log.d("onDataAvailable", "config is ${_config.value}")
    }

    override fun onError(exception: Exception) {
        Log.d("onError", "data is $exception")
    }

}