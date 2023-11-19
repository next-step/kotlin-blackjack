package blackjack.business.participants

import blackjack.business.card.Card
import blackjack.business.util.BlackJackConst

open class Player(val name: String, private val playerCards: PlayerCards = PlayerCards()) {

    val score: Int
        get() = playerCards.sum()
    val cards: List<Card>
        get() = playerCards.cards

    fun addCard(card: Card) {
        playerCards.add(card)
    }

    open fun canDrawCard(): Boolean {
        return playerCards.sum() < BlackJackConst.BLACKJACK
    }

    fun addCards(playerCardsList: List<Card>) {
        playerCards.addAll(playerCardsList)
    }

    open fun isBust(): Boolean {
        return playerCards.isBust()
    }
}
