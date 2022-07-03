package blackjack.domain.user

import blackjack.domain.Card
import blackjack.domain.Score

class Player(override val name: String) : User() {

    override fun drawable(): Boolean {
        val score = Score(cards)

        return !(score.isBust() || score.isBlackjack())
    }

    override fun addCard(card: Card) {
        check(drawable()) { "Player can not drawable" }
        cards.add(card)
    }
}
