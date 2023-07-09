package blackjack.domain.participant

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Dealer(
    hand: Hand = Hand.init
) : Participant(hand) {
    fun play(drawCard: () -> Card) {
        if (canDraw().not() || hasPlayedBefore()) return
        addCard(drawCard)
    }

    private fun canDraw(): Boolean = this.score().value <= 16

    private fun hasPlayedBefore(): Boolean {
        val cardsCount = hand.cards.size
        return cardsCount > START_CARD_COUNT
    }
}
