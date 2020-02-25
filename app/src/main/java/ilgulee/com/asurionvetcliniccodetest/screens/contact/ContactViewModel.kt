package ilgulee.com.asurionvetcliniccodetest.screens.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ilgulee.com.asurionvetcliniccodetest.repository.ConfigRepository
import ilgulee.com.asurionvetcliniccodetest.repository.PetRepository
import java.util.*


class ContactViewModel : ViewModel() {
    private val petRepository = PetRepository()
    private val configRepository = ConfigRepository()

    val petList = petRepository.petList
    val config = configRepository.config

    private val _navigateToPetDetail = MutableLiveData<String>()
    val navigateToPetDetail: LiveData<String>
        get() = _navigateToPetDetail

    private val _isBusinessDayAndTime = MutableLiveData<Boolean>()
    val isBusinessDayAndTime: LiveData<Boolean>
        get() = _isBusinessDayAndTime

    fun onPetClicked(url: String) {
        _navigateToPetDetail.value = url
    }

    fun onPetDetailedNavigated() {
        _navigateToPetDetail.value = null
    }

    fun chatClicked() {
        checkBusinessDayAndTime()
    }

    fun callClicked() {
        checkBusinessDayAndTime()
    }

    private fun checkBusinessDayAndTime() {
        val now = Calendar.getInstance()
        val today = now.get(Calendar.DAY_OF_WEEK)
        val hour = now.get(Calendar.HOUR_OF_DAY)
        if ((today != Calendar.SATURDAY) && (today != Calendar.SUNDAY)) {
            if (hour in 9..18) {
                _isBusinessDayAndTime.value = true
            }
        } else {
            _isBusinessDayAndTime.value = false
        }

    }

    fun confirmclicked(){
        _isBusinessDayAndTime.value= null
    }
}