package domain.player

import exception.IllegalPlayerNameException

@JvmInline
value class PlayerName(val name: String) {
    init {
        if (name.isNullOrBlank()) {
            throw IllegalPlayerNameException()
        }
    }
}
