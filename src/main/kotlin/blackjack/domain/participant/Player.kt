package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.hand.Hand

class Player(
    val name: String,
    val betAmount: BetAmount,
    hand: Hand = Hand.init
) : Participant(hand) {
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
