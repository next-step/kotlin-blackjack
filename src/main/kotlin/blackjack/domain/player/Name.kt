package blackjack.domain.player

import blackjack.error.InvalidPlayerNameException

@JvmInline
value class Name(val name: String) {
    init {
        if (name.isNullOrBlank()) {
            throw InvalidPlayerNameException(name)
        }
    }
}
