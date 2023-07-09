package blackjack.domain.participant

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Player(
    name: String,
    hand: Hand = Hand.init
) : Participant(name, hand) {
    fun play(
        isHit: () -> Boolean,
        drawCard: () -> Card
    ) {
        while (canDraw() && isHit()) {
            addCard(drawCard)
        }
    }

    private fun canDraw(): Boolean = this.isBust().not()
}
