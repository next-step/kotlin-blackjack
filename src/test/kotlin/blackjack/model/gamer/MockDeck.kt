package blackjack.model.gamer

import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Deck
import blackjack.model.trump.Suit

class MockDeck(
    private val deck: MutableList<Card> = buildDeck()
) : Deck, MutableList<Card> by deck {
    fun peekCard(cardNumber: CardNumber, suit: Suit): Card {
        return deck.find { it == Card(cardNumber, suit) }!!
    }

    override fun draw(): Card {
        return deck.random()
    }

    companion object {
        private fun buildDeck() =
            Suit.values().flatMap { suit -> CardNumber.values().map { cardNumber -> Card(cardNumber, suit) } }
                .shuffled().toMutableList()
    }
}
