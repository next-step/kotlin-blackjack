package blackjack.domain

import java.util.Random

class RandomDeck private constructor(
    private val cards: List<Card> = listOf()
) : Deck {
    private val random = Random()

    override fun init(): List<Card> {
        return listOf(getNewCard(), getNewCard())
    }

    override fun hit(): Card {
        return getNewCard()
    }

    private fun getNewCard(): Card = cards[random.nextInt(ONE_DECK_CARD_COUNT)]

    companion object {
        private const val ONE_DECK_CARD_COUNT = 52

        fun from(): Deck {
            val cards = CardSuit.values()
                .flatMap { cardSuit ->
                    CardNumber.values()
                        .map { Card(cardSuit, it) }
                        .toList()
                }
                .shuffled()
            return RandomDeck(cards)
        }
    }
}
