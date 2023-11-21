package blackjack.business.participants

import blackjack.business.card.Card

abstract class Player(val name: String, val playerCards: PlayerCards = PlayerCards()) {

    val cardsCount: Int
        get() = playerCards.size
    val score: Int
        get() = playerCards.sum()
    val cards: List<Card>
        get() = playerCards.cards

    abstract fun addCard(card: Card): Player

    abstract fun canDrawCard(): Boolean

    abstract fun addCards(playerCardsList: List<Card>): Player

    fun isBust(): Boolean = playerCards.isBust()
    fun isNaturalBlackJack(): Boolean = playerCards.isNaturalBlackJack()
}
