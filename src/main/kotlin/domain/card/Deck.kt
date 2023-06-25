package domain.card

import domain.card.util.DeckGenerator
import domain.game.BlackjackGame

fun MutableList<Card>.pop(): Card = this.removeAt(0)

class Deck(private val cards: MutableList<Card>) {
    val cardCount
        get() = cards.size

    fun issueCard(): Card {
        if (cards.isEmpty()) {
            reloadCards()
        }
        return cards.pop()
    }

    private fun reloadCards() {
        val newDeck = DeckGenerator.makeDeck(BlackjackGame.BLACKJACK_GAME_DECK_SIZE)
        cards.addAll(newDeck.cards)
    }
}
