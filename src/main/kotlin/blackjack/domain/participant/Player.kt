package blackjack.domain.participant

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Player(
    name: String,
    hand: Hand = Hand.init
) : Participant(name, hand) {
    fun start(drawCard: () -> Card) {
        val cards = List(START_CARD_COUNT) { drawCard() }
        addCards { cards }
    }

    fun play(
        isHit: () -> Boolean,
        drawCard: () -> Card
    ) {
        while (canDraw() && isHit()) {
            addCard(drawCard)
        }
    }

    private fun canDraw(): Boolean = this.isBust().not()

    companion object {
        const val START_CARD_COUNT = 2
    }
}
