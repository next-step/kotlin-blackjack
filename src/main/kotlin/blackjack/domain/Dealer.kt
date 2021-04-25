package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardPack
import blackjack.domain.state.State
import blackjack.domain.state.notstarted.NotStarted

class Dealer(
    state: State = NotStarted()
) : Participant {

    val player = Player("딜러", state)
    val state
        get() = player.state

    val name: String = player.name
    val cardSize
        get() = player.cardSize
    private val isUnderSixteen: Boolean
        get() = this.cardPointSum() <= DEALER_POINT_TO_TAKE_MORE_CARD

    override fun takeCard(card: Card) {
        player.takeCard(card)
    }

    override fun takeFirstTwoCards(card1: Card, card2: Card) {
        player.takeFirstTwoCards(card1, card2)
    }

    override fun cardPointSum(): Int {
        return player.cardPointSum()
    }

    fun takeCardUntilSixteen(cardPack: CardPack) {
        while (isUnderSixteen) {
            takeCard(cardPack.poll())
        }
        if (state.isRunning) {
            stay()
        }
    }

    private fun stay() {
        player.stay()
    }

    companion object {
        private const val DEALER_POINT_TO_TAKE_MORE_CARD = 16
    }
}
