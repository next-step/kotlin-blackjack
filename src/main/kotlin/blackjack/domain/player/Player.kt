package blackjack.domain.player

import blackjack.domain.Card

class Player(val name: String) : AbstractPlayer() {
    override fun drawCard(newCard: Card) {
        cards.add(newCard)
    }

    override fun isBust(): Boolean {
        return calculateCard() > 21
    }
}
