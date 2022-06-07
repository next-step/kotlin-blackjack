package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardNumberTest {
    @Test
    fun `Enum 으로 Ace, Two ~ Ten, Jack, Queen, King 이 있다`() {
        assertThat(CardNumber.values().size).isEqualTo(13)
        assertThat(CardNumber.values()).contains(
            CardNumber.Ace, CardNumber.Two, CardNumber.Ten, CardNumber.Jack, CardNumber.Queen, CardNumber.King
        )
    }

    @ParameterizedTest
    @CsvSource("Ace,1,11", "Two,2,", "Ten,10,", "Jack,10,", "Queen,10,", "King,10,")
    fun `각각 값은 대응되는 숫자를 가지며 A는 1, 11 두 숫자에 대응, K, Q, J는 10이다`(cardNumber: CardNumber, score1: Int, score2: Int?) {
        assertThat(cardNumber.scores).isEqualTo(listOf(score1, score2).filterNotNull().map { Score(it) })
    }
}
