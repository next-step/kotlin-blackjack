package blackjack.domain

import blackjack.exception.InvalidPlayerNameException

data class Player(
    val name: String,
) {
    init {
        if (name.isEmpty()) {
            throw InvalidPlayerNameException()
        }
    }
}
