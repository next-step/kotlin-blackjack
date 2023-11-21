package blackJack.model

import blackJack.model.enums.Rank
import blackJack.model.enums.Suit

data class Card(
    val suit: Suit,
    val rank: Rank
)

class CardDeck(val cards: List<Card>) {
    companion object {
        fun of(): CardDeck {
            val cards = generateAllCards()
            return CardDeck(cards)
        }

        private fun generateAllCards(): List<Card> {
            return Suit.values().flatMap { suit ->
                generateCardsForSuit(suit)
            }
        }

        private fun generateCardsForSuit(suit: Suit): List<Card> {
            return Rank.values().map { rank ->
                Card(suit, rank)
            }
        }
    }
}
