package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.util.COUNT_THRESHOLD

data class Player(
    val name: String,
    var status: PlayerStatus = PlayerStatus.HIT,
    val hand: Hand = Hand()
) {

    fun addCards(vararg cards: Card) {
        cards.forEach { hand.add(it) }
        checkBust()
    }

    fun score(): Int {
        return hand.score()
    }

    private fun checkBust() {
        val score = hand.score()
        if (score > COUNT_THRESHOLD) {
            status = PlayerStatus.BUST
        }
    }
}
