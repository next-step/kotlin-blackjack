package blackjack.business.participants

import blackjack.business.card.Card

interface Player {
    val score: Int
    val name: String
    val cards: List<Card>
    fun canDrawCard(): Boolean
    fun isBust(): Boolean
    fun addCard(card: Card)
    fun addCards(playerCardsList: List<Card>)
}
