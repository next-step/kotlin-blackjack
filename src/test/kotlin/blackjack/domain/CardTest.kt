package blackjack.domain

import blackjack.cardOf
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "ACE,1",
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
    fun `카드의 점수는 심볼에 의해 정해진다`(symbol: Symbol, value: Int) {
        val card = cardOf(Type.CLUBS, symbol)

        card.value shouldBe value
    }

    @Test
    fun `King, Queen, Jack은 10으로 계산한다`() {
        val king = cardOf(Type.CLUBS, Symbol.KING)
        val queen = cardOf(Type.CLUBS, Symbol.QUEEN)
        val jack = cardOf(Type.CLUBS, Symbol.JACK)

        king.value shouldBe 10
        queen.value shouldBe 10
        jack.value shouldBe 10
    }
}
