package blackjack.domain.player

import blackjack.domain.Card

class Dealer : AbstractPlayer() {

    override fun drawCard(newCard: Card) {
        TODO("Not yet implemented")
    }

    override fun isBust(): Boolean {
        return calculateCard() > 16
    }
}