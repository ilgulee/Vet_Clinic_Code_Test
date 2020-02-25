package ilgulee.com.asurionvetcliniccodetest.model

data class Config(
    var isChatEnabled: Boolean = false,
    var isCallEnabled: Boolean = false,
    var workHours: String = ""
)