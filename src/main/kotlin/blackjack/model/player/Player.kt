package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

open class Player protected constructor(
    val playerName: PlayerName,
    val cards: Cards = Cards(),
    var needMoreCard: Boolean = true,
) {
    val cardSize
        get() = cards.size

    val sumOfCardScore
        get() = cards.sumOfScore

    val name
        get() = playerName.name

    open val isDealer
        get() = false

    fun isAllScoreGreaterThan(other: Int) = sumOfCardScore.isAllScoreGreaterThan(other)

    fun beats(other: Player, boundaryScore: Int) =
        sumOfCardScore.findNearestScoreEqualOrLessThan(boundaryScore) > other.sumOfCardScore.findNearestScoreEqualOrLessThan(boundaryScore)

    open fun receiveCard(card: Card) {
        cards.addOne(card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (playerName != other.playerName) return false

        return true
    }

    override fun hashCode() = playerName.hashCode()

    companion object {
        fun from(name: String) = Player(PlayerName(name))

        fun from(name: String, cards: Cards) = Player(PlayerName(name), cards)
    }
}
