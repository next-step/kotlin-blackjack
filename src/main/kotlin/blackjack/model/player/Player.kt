package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

open class Player protected constructor(
    val name: PlayerName,
    val cards: Cards = Cards(),
    var needMoreCard: Boolean = true,
) {
    val cardSize
        get() = cards.size

    val sumOfCardScore
        get() = cards.sumOfScore

    open val isDealer
        get() = false

    fun isScoreGreaterThan(other: Player) = sumOfCardScore.isGreaterThan(other.sumOfCardScore.score1) ||
            sumOfCardScore.isGreaterThan(other.sumOfCardScore.score2)

    fun isScoreLessThan(other: Player) = sumOfCardScore.isLessThan(other.sumOfCardScore.score1) &&
            sumOfCardScore.isLessThan(other.sumOfCardScore.score2)

    open fun receiveCard(card: Card) {
        cards.addOne(card)
    }

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
