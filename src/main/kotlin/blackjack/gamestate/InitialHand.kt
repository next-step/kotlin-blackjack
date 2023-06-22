package blackjack.gamestate

import blackjack.Card
import blackjack.Cards
import java.lang.IllegalStateException

class InitialHand(
    val cards: Cards = Cards(),
): GameState {
    override fun draw(card: Card): GameState {
        val cards = this.cards.addCard(card)
        return InitialHand(cards)
    }

    override fun stay() = throw IllegalStateException("2장을 받기전에는 카드를 그만받을 수 없다.")
}