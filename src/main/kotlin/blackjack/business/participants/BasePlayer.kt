package blackjack.business.participants

import blackjack.business.card.Card
import java.util.concurrent.atomic.AtomicReference

abstract class BasePlayer(override val name: String, playerCards: PlayerCards = PlayerCards()) : Player {
    private val playerCardsRef = AtomicReference(playerCards)

    override val score: Int
        get() = playerCards.sum()
    override val cards: List<Card>
        get() = playerCards.cards
    protected val playerCards: PlayerCards
        get() = playerCardsRef.get()

    override fun addCard(card: Card) {
        playerCardsRef.updateAndGet { it.add(card) }
    }

    abstract override fun canDrawCard(): Boolean

    override fun addCards(playerCardsList: List<Card>) {
        playerCardsRef.updateAndGet { it.addAll(playerCardsList) }
    }

    override fun isBust(): Boolean {
        return playerCards.isBust()
    }
}
