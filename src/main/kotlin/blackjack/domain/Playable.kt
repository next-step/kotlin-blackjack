package blackjack.domain

import blackjack.domain.card.BLACKJACK
import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.OpenCards
import blackjack.domain.player.Status

open class Playable(val hand: Hand) {
    var status: Status = Status.NOT_STARTED
        private set
    fun isFinished(): Boolean = status in setOf(Status.BUST, Status.BLACKJACK, Status.STAY)
    fun bust(): Boolean = total() > BLACKJACK
    fun blackjack(): Boolean = total() == BLACKJACK && hand.getCards().size == 2
    fun open(openCards: OpenCards) {
        hand.add(openCards.first, openCards.second)
        status = Status.OPEN
        if (blackjack()) {
            status = Status.BLACKJACK
        }
    }
    fun hit(card: Card) {
        hand.add(card)

        if (hand.bust()) {
            status = Status.BUST
            return
        }

        if (hand.blackjack()) {
            status = Status.BLACKJACK
            return
        }

        status = Status.HIT
    }

    fun stay() {
        if (!isFinished()) {
            status = Status.STAY
        }
    }

    fun total(): Int = hand.total()

}
