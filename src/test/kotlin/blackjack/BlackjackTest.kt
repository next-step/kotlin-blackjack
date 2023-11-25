package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BlackjackTest {

    @ParameterizedTest
    @CsvSource(value = ["7, 8, 15", "6, 5, 11"])
    fun `카드의 합을 계산`(card1: String, card2: String, expect: Int) {
        val cards = Cards(card1, card2)

        val actual = cards.sum()

        assertThat(actual).isEqualTo(expect)
    }
}
