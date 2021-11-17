package blackjack.domain.player

import blackjack.domain.card.Card

interface CardFunction {
    fun addCard(card: Card)
    fun getCardSum(): Int
}
