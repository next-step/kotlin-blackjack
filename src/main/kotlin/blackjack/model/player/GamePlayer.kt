package blackjack.model.player

import blackjack.model.card.Cards

class GamePlayer(
    name: String,
    cards: Cards = Cards()
) : Player(name, cards) {

    override fun gameBatting(cards: Cards) {
        this.cards.addCards(cards)
    }
}
