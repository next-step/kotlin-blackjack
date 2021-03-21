package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.Card
import blackjack.domain.card.Hands

abstract class User(val userName: UserName) {
    val hands = Hands(mutableListOf())

    open fun draw(card: Card, decider: DrawDecider) {
        if (canDraw() && decider.isDraw()) {
            hands.draw(card)
        }
    }

    fun calculateScore() = hands.calculateScore()

    abstract fun canDraw(): Boolean
}
