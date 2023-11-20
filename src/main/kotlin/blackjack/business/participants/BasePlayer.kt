package blackjack.business.participants

import blackjack.business.card.Card

abstract class BasePlayer(override val name: String, protected val playerCards: PlayerCards = PlayerCards()) : Player {

    override val score: Int
        get() = playerCards.sum()
    override val cards: List<Card>
        get() = playerCards.cards

    override fun addCard(card: Card) {
        playerCards.add(card)
    }

    abstract override fun canDrawCard(): Boolean

    override fun addCards(playerCardsList: List<Card>) {
        playerCards.addAll(playerCardsList)
    }

    override fun isBust(): Boolean {
        return playerCards.isBust()
    }
}
