package blackjack.domain.participant

import blackjack.domain.PlayerHand
import blackjack.domain.card.Card

class Dealer(
    hand: PlayerHand = PlayerHand.init
) : Participant(DEFAULT_NAME, hand) {
    fun start(drawCard: () -> Card) {
        val cards = List(START_CARD_COUNT) { drawCard() }
        addCards { cards }
    }

    fun play(drawCard: () -> Card) {
        if (canDraw().not() || hasPlayedBefore()) return
        addCard(drawCard)
    }

    private fun canDraw(): Boolean = this.score().value <= 16

    private fun hasPlayedBefore(): Boolean {
        val cardsCount = hand.cards.size
        return cardsCount > START_CARD_COUNT
    }

    companion object {
        const val DEFAULT_NAME = "딜러"
        const val START_CARD_COUNT = 2
    }
}
