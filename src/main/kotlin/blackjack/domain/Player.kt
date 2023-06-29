package blackjack.domain

import blackjack.domain.Cards.Companion.EMPTY_CARDS

class Player(val playerName: PlayerName) {

    private var _cards: Cards = EMPTY_CARDS
    val cards: Cards
        get() = _cards

    fun receiveCard(card: Card): Cards {
        addCard(card)
        return cards
    }

    private fun addCard(card: Card) {
        _cards = _cards.addCard(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (playerName != other.playerName) return false

        return true
    }

    override fun hashCode(): Int {
        return playerName.hashCode()
    }

    companion object {
        fun of(name: String): Player = Player(PlayerName(name))
    }
}

@JvmInline
value class PlayerName(val name: String)
