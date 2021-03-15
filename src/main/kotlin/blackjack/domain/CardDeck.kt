package blackjack.domain

import blackjack.domain.Card.Companion.NUMBERS
import blackjack.domain.Card.Companion.PATTERNS
import kotlin.random.Random

object CardDeck {
    private const val AVAILABLE_CARD = true
    private const val DUPLICATED_CARD = false

    private val HEARTS = NUMBERS.map { number ->
        Card.makeCard(number, Pattern.HEART)
    }
    private val DIAMONDS = NUMBERS.map { number ->
        Card.makeCard(number, Pattern.DIAMOND)
    }
    private val SPADES = NUMBERS.map { number ->
        Card.makeCard(number, Pattern.SPADE)
    }
    private val CLOVERS = NUMBERS.map { number ->
        Card.makeCard(number, Pattern.CLOVER)
    }
    val CARDS =
        (HEARTS + DIAMONDS + SPADES + CLOVERS)
            .associateWith { AVAILABLE_CARD }
            .toMutableMap()

    fun drawCard(): Card {
        val randomPattern = PATTERNS[Random.nextInt(0, PATTERNS.size - 1)]
        val randomNumber = NUMBERS[Random.nextInt(0, NUMBERS.size - 1)]
        val card = Card.makeCard(randomNumber, randomPattern)

        if (!isDuplicateCard(card)) {
            CARDS[card] = false
            return card
        }
        return drawCard()
    }

    private fun isDuplicateCard(card: Card?): Boolean {
        if (CARDS[card] == DUPLICATED_CARD) {
            return true
        }
        return false
    }
}
