package ilgulee.com.asurionvetcliniccodetest.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PetDetailViewModelFactory(private val url: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetDetailViewModel::class.java)) {
            return PetDetailViewModel(url) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}