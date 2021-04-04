package blackjack.domain.card

import blackjack.domain.Card

interface CardFactory {
    fun createCards(): List<Card>
}
