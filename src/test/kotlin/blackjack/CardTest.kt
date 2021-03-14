package blackjack


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        private val NUMBERS =
            listOf<String>("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "J", "Q", "K")
        private val PATTERNS = listOf(Pattern.HEART, Pattern.DIAMOND, Pattern.SPADE, Pattern.CLOVER)
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
        private val CARDS =
            (HEARTS + DIAMONDS + SPADES + CLOVERS)
                .associateWith { card -> true }
                .toMutableMap()

        fun drawCard(pattern: Pattern, number: String): Card? {
            val card = Card(number to pattern)
            if (CARDS[card] == true) {
                CARDS[card] = false
                return card
            }
            return null
        }
    }
}

enum class Pattern(val pattern: String) {
    HEART("하트"),
    DIAMOND("다이아"),
    SPADE("스페이드"),
    CLOVER("클로버")
}

class CardTest {
    @Test
    fun `같은 카드 2번 드로우 하면 null 값이 출력`() {
        val firstDraw = Card.drawCard(Pattern.HEART, "A")
        val secondDraw = Card.drawCard(Pattern.HEART, "A")

        assertThat(firstDraw).isNotNull
        assertThat(secondDraw).isNull()
    }
}
