package blackjack.card

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardSymbolTest {

    @ParameterizedTest
    @CsvSource(
        "TWO, 2",
        "THREE, 3",
        "FOUR, 4",
        "FIVE, 5",
        "SIX, 6",
        "SEVEN, 7",
        "EIGHT, 8",
        "NINE, 9",
        "TEN, 10",
    )
    fun `2~9 까지의 숫자의 값은 카드 숫자를 기본으로 한다`(symbol: String, value: Int) {
        TODO()
    }

    @ParameterizedTest
    @CsvSource(
        "KING, 10",
        "QUEEN, 10",
        "JACK, 10",
    )
    fun `King, Queen, Jack 의 값은 각각 10으로 계산한다`(symbol: String, value: Int) {
        TODO()
    }

    @Test
    fun `Ace 의 값은 1 또는 11로 계산할 수 있다`() {
        TODO()
    }
}
