package blackjack.domain

import blackjack.domain.state.State
import blackjack.domain.state.notstarted.NotStarted
import java.math.BigDecimal

class Dealer(
    cards: Set<Card> = emptySet()
) : Participant {

    val player = Player("딜러", cards)
    val state
        get() = player.state

    val name: String = player.name
    val isUnderSixteen: Boolean
        get() = this.cardPointSum() <= DEALER_POINT_TO_TAKE_MORE_CARD
    val cardSize
        get() = player.cardSize
    override val isBlackjack: Boolean
        get() = player.isBlackjack

    override fun takeCard(card: Card) {
        player.takeCard(card)
    }

    override fun takeFirstTwoCards(card1: Card, card2: Card) {
        player.takeFirstTwoCards(card1, card2)
    }

    override fun cardPointSum(): Int {
        return player.cardPointSum()
    }

    fun profit(playerProfits: List<Profit>): BigDecimal {
        return playerProfits.fold(BigDecimal.ZERO) { acc, profit -> acc + profit.amount } * BigDecimal("-1")
    }

    companion object {
        private const val DEALER_POINT_TO_TAKE_MORE_CARD = 16
    }
}
