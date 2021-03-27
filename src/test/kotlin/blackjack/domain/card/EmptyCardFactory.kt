package blackjack.domain.card

import blackjack.domain.Card

class EmptyCardFactory : CardFactory {
    override fun createCards(): List<Card> {
        return mutableListOf()
    }
}
