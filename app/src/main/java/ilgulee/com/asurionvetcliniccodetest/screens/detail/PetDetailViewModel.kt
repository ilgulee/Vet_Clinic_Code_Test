package ilgulee.com.asurionvetcliniccodetest.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PetDetailViewModel(private val url: String) : ViewModel() {
    private val _contentUrl: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val contentUrl: LiveData<String>
        get() = _contentUrl

    init {
        _contentUrl.value = url
    }
}