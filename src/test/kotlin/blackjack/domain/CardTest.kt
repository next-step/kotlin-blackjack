package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    private fun Card(type: Type, symbol: Symbol): Card {
        return Card.CARD_POOL.find { it.type == type && it.symbol == symbol }!!
    }

    @ParameterizedTest
    @CsvSource(
        "ACE,11",
        "TWO,2",
        "THREE,3",
        "FOUR,4",
        "FIVE,5",
        "SIX,6",
        "SEVEN,7",
        "EIGHT,8",
        "NINE,9",
        "TEN,10",
        "JACK,10",
        "QUEEN,10",
        "KING,10",
    )
    fun `카드의 숫자는 심볼에 의해 정해진다`(symbol: Symbol, value: Int) {
        val card = Card(Type.CLOVER, symbol)

        card.value shouldBe value
    }

    @ParameterizedTest
    @CsvSource(
        "10,21",
        "11,12"
    )
    fun `ACE는 1 또는 11로 계산될 수 있다`(base: Int, sum: Int) {
        val card = Card(Type.CLOVER, Symbol.ACE)

        base + card shouldBe sum
    }

    @Test
    fun `King, Queen, Jack은 10으로 계산한다`() {
        val king = Card(Type.CLOVER, Symbol.KING)
        val queen = Card(Type.CLOVER, Symbol.QUEEN)
        val jack = Card(Type.CLOVER, Symbol.JACK)

        king.value shouldBe 10
        queen.value shouldBe 10
        jack.value shouldBe 10
    }

    @Test
    fun `Card + Card 는 카드 숫자 값의 합을 반환한다`() {
        val three = Card(Type.CLOVER, Symbol.THREE)
        val king = Card(Type.CLOVER, Symbol.KING)

        three + king shouldBe 13
    }

    @Test
    fun `Card + Int 는 카드 숫자 값의 합을 반환한다`() {
        val three = Card(Type.SPADE, Symbol.THREE)
        val king = Card(Type.CLOVER, Symbol.KING)

        three + 10 shouldBe 13
        3 + king shouldBe 13
    }
}
