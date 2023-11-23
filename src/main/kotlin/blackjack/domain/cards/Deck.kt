package blackjack.domain.cards

import blackjack.domain.card.Card

class Deck(val deckCards: DeckCards) {

    fun draw(): Card = deckCards.drawTop()

    fun shuffle() {
        deckCards.shuffle()
    }

    fun cardCount(): Int = deckCards.size

    companion object {
        fun fullDeck(): Deck {
            return Deck(
                DeckCards.fullDeckCards()
            )
        }
    }
}
