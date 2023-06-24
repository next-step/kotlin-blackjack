package domain.card

import domain.card.util.DeckGenerator
import domain.game.BlackjackGame

fun MutableList<Card>.pop(): Card = this.removeAt(0)

class Deck(private val Cards: MutableList<Card>) {
    val cardCount
        get() = Cards.size

    fun issueCard(): Card {
        if (Cards.isEmpty()) {
            reloadCards()
        }
        return Cards.pop()
    }

    private fun reloadCards() {
        val newDeck = DeckGenerator.makeDeck(BlackjackGame.BLACKJACK_GAME_DECK_SIZE)
        Cards.addAll(newDeck.Cards)
    }
}
