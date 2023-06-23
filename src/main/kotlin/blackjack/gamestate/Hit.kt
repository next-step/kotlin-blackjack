package blackjack.gamestate

import blackjack.Card
import blackjack.Cards

class Hit(
    val cards: Cards,
) : GameState {
    override fun draw(card: Card): GameState {
        val cards = this.cards.addCard(card)
        return Hit(cards)
    }

    override fun stay() = Stay()
}