package ilgulee.com.asurionvetcliniccodetest.repository

import android.os.AsyncTask
import ilgulee.com.asurionvetcliniccodetest.model.Pet
import org.json.JSONException
import org.json.JSONObject

class PetJsonParse(private val listener: OnDataAvailable) :
    AsyncTask<String, Void, ArrayList<Pet>>() {
    interface OnDataAvailable {
        fun onDataAvailable(data: List<Pet>)
        fun onError(exception: Exception)
    }

    override fun doInBackground(vararg params: String): ArrayList<Pet> {
        val petList = ArrayList<Pet>()
        try {
            val jsonObject = JSONObject(params[0])
            val jsonArray = jsonObject.getJSONArray("pets")
            for (i in 0 until jsonArray.length()) {
                val petJson = jsonArray.getJSONObject(i)
                val pet = Pet()
                pet.contentUrl = petJson.getString("content_url")
                pet.dateAdded = petJson.getString("date_added")
                pet.imageUrl = petJson.getString("image_url")
                pet.title = petJson.getString("title")
                petList.add(pet)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            listener.onError(e)
        }
        return petList
    }

    override fun onPostExecute(result: ArrayList<Pet>) {
        super.onPostExecute(result)
        listener.onDataAvailable(result)
    }
}