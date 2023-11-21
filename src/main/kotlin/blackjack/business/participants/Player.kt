package blackjack.business.participants

import blackjack.business.card.Card
import java.util.concurrent.atomic.AtomicReference

abstract class Player(val name: String, playerCards: PlayerCards = PlayerCards()) {
    private val playerCardsRef = AtomicReference(playerCards)

    val score: Int
        get() = playerCards.sum()
    val cards: List<Card>
        get() = playerCards.cards
    protected val playerCards: PlayerCards
        get() = playerCardsRef.get()

    fun addCard(card: Card) {
        playerCardsRef.updateAndGet { it.add(card) }
    }

    abstract fun canDrawCard(): Boolean

    fun addCards(playerCardsList: List<Card>) {
        playerCardsRef.updateAndGet { it.addAll(playerCardsList) }
    }

    fun isBust(): Boolean = playerCards.isBust()
    fun isNaturalBlackJack(): Boolean = playerCards.isNaturalBlackJack()
}
