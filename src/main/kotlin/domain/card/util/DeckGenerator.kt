package domain.card.util

import domain.card.Card
import domain.card.CardNumber
import domain.card.Deck
import domain.card.Suit
import domain.game.BlackjackGame

object DeckGenerator {

    fun makeDeck(deckSize: Int = BlackjackGame.BLACKJACK_GAME_DECK_SIZE): Deck {
        val cards = List(deckSize) { makeDeck() }
            .flatten()
            .shuffled()
            .toMutableList()
        return Deck(cards)
    }

    private fun makeDeck(): List<Card> {
        return Suit.values().map {
            makeCards(it, CardNumber.values())
        }.flatten()
    }

    private fun makeCards(suit: Suit, cardNumbers: Array<CardNumber>): List<Card> {
        return cardNumbers.map { Card(suit, it) }
    }
}
