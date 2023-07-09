package blackjack.domain.participant

import blackjack.domain.BetAmount
import blackjack.domain.Hand
import blackjack.domain.card.Card

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
