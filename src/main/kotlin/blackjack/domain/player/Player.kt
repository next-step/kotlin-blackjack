package blackjack.domain.player

import blackjack.domain.Cards

class Player(private val name: String, private val cards: Cards) {
    init {
        require(name.isNotBlank())
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
