package blackjack.business.participants

import blackjack.business.canDrawCardStrategy.DealerCanDrawCardStrategy
import blackjack.business.card.Card

class Dealer {
    private val _dealerCards: PlayerCards = PlayerCards(canDrawCardStrategy = DealerCanDrawCardStrategy())
    val cards: List<Card>
        get() = _dealerCards.cards

    fun addCard(card: Card) {
        _dealerCards.add(card)
    }

    fun canDrawCard(): Boolean {
        return _dealerCards.canDrawCard()
    }

    fun isBust(): Boolean {
        return _dealerCards.isBust()
    }
}
