package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.Card
import blackjack.domain.card.Hands

class Player(val playerName: PlayerName) {
    val hands = Hands(mutableListOf())
    val score
        get() = hands.calculateScore()

    fun draw(card: Card, decider: DrawDecider) {
        if (canDraw() && decider == DrawDecider.DRAW) {
            hands.draw(card)
        }
    }

    fun canDraw() = hands.calculateScore() <= DRAW_CONDITION

    companion object {
        private const val DRAW_CONDITION = 21
    }
}
