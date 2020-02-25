package ilgulee.com.asurionvetcliniccodetest.repository

import android.os.AsyncTask
import ilgulee.com.asurionvetcliniccodetest.model.Config
import org.json.JSONException
import org.json.JSONObject

class ConfigJsonParse(private val listener: OnDataAvailable) :
    AsyncTask<String, Void, Config>() {
    interface OnDataAvailable {
        fun onDataAvailable(data: Config)
        fun onError(exception: Exception)
    }

    override fun doInBackground(vararg params: String): Config {
        val config = Config()
        try {
            val jsonObject = JSONObject(params[0])
            val settings = jsonObject.getJSONObject("settings")
            val isChatEnable = settings.getBoolean("isChatEnabled")
            val isCallEnabled = settings.getBoolean("isCallEnabled")
            val workHours = settings.getString("workHours")
            config.isCallEnabled = isCallEnabled
            config.isChatEnabled = isChatEnable
            config.workHours = workHours
        } catch (e: JSONException) {
            e.printStackTrace()
            listener.onError(e)
        }
        return config
    }

    override fun onPostExecute(result: Config) {
        super.onPostExecute(result)
        listener.onDataAvailable(result)
    }
}