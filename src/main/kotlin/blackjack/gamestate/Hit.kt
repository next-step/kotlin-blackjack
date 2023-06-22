package blackjack.gamestate

import blackjack.Card
import blackjack.Cards

class Hit(
    val cards: Cards,
) : GameState {
    override fun draw(card: Card): GameState {
        TODO("Not yet implemented")
    }

    override fun stay() {
        TODO("Not yet implemented")
    }
}