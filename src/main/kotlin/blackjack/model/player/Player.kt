package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

class Player private constructor(
    val name: PlayerName,
    val cards: Cards = Cards()
) {
    val cardSize
        get() = cards.size

    val sumOfCardScore
        get() = cards.sumOfScore

    fun receiveCard(card: Card) = cards.addOne(card)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode() = name.hashCode()

    companion object {
        fun from(name: String) = Player(PlayerName(name))

        fun from(name: String, cards: Cards) = Player(PlayerName(name), cards)
    }
}
