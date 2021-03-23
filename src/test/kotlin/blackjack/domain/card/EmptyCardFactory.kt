package blackjack.domain.card

import blackjack.domain.Card

class EmptyCardFactory : CardFactory {
    override fun createCards(): MutableList<Card> {
        return mutableListOf()
    }
}
