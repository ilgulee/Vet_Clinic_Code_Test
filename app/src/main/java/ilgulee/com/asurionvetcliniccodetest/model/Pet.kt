package ilgulee.com.asurionvetcliniccodetest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pet(
    var imageUrl: String = "",
    var title: String = "",
    var contentUrl: String = "",
    var dateAdded: String = ""
) : Parcelable