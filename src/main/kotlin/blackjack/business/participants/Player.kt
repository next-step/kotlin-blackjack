package blackjack.business.participants

import blackjack.business.card.Card

class Player private constructor(val name: String, playerCards: PlayerCards) {

    private val _cards: PlayerCards = playerCards

    val score: Int
        get() = _cards.sum()
    val cards: List<Card>
        get() = _cards.cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun canDrawCard(): Boolean {
        return _cards.sum() < 21
    }

    fun addCards(playerCards: List<Card>) {
        _cards.addAll(playerCards)
    }

    fun isBust(): Boolean {
        return _cards.isBust()
    }

    companion object {
        fun from(mame: String, cards: List<Card> = listOf()): Player {
            return Player(mame, PlayerCards(cards))
        }

        fun from(mame: String, cards: PlayerCards): Player {
            return Player(mame, cards)
        }
    }
}
