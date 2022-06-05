package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.util.COUNT_THRESHOLD

data class Player(
    val name: String
) {
    val hand: Hand = Hand()
    var status: PlayerStatus = PlayerStatus.HIT
        private set

    fun addCards(vararg cards: Card) {
        cards.forEach { hand.add(it) }
        checkBust()
    }

    fun score(): Int {
        return hand.score()
    }

    fun changeStatus(status: PlayerStatus) {
        this.status = status
    }

    private fun checkBust() {
        val score = hand.score()
        if (score > COUNT_THRESHOLD) {
            status = PlayerStatus.BUST
        }
    }
}
