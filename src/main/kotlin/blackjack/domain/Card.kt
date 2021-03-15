package blackjack.domain

import kotlin.random.Random

class Card private constructor(val value: Pair<String, Pattern>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value.first + value.second.pattern
    }

    companion object {
        private const val AVAILABLE_CARD = true
        private const val DUPLICATED_CARD = false
        private val NUMBERS =
            listOf<String>("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "J", "Q", "K")
        private val PATTERNS = listOf(
            Pattern.HEART,
            Pattern.DIAMOND,
            Pattern.SPADE,
            Pattern.CLOVER
        )
        private val HEARTS = NUMBERS.map { number ->
            Card(number to Pattern.HEART)
        }
        private val DIAMONDS = NUMBERS.map { number ->
            Card(number to Pattern.DIAMOND)
        }
        private val SPADES = NUMBERS.map { number ->
            Card(number to Pattern.SPADE)
        }
        private val CLOVERS = NUMBERS.map { number ->
            Card(number to Pattern.CLOVER)
        }
        val CARDS =
            (HEARTS + DIAMONDS + SPADES + CLOVERS)
                .associateWith { card -> AVAILABLE_CARD }
                .toMutableMap()

        fun drawCard(): Card {
            val randomPattern  = PATTERNS[Random.nextInt(0, PATTERNS.size - 1)]
            val randomNumber = NUMBERS[Random.nextInt(0, NUMBERS.size - 1)]
            val card = Card(randomNumber to randomPattern)

            if (!isDuplicateCard(card)) {
                CARDS[card] = false
                return card
            }
            return drawCard()
        }

        private fun isDuplicateCard(card: Card?): Boolean {
            if(CARDS[card] == DUPLICATED_CARD) {
                return true
            }
            return false
        }
    }
}
