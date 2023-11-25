package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

    @ParameterizedTest
    @CsvSource(value = ["K, 5, 15", "J, 3, 13", "Q, 8, 18"])
    fun `문자 카드는 10으로 계산`(card1: String, card2: String, expect: Int) {
        val cards = Cards(card1, card2)

        val actual = cards.sum()

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `Ace를 11로 계산`() {
        val cards = Cards("A", "10")

        val actual = cards.sum()

        assertThat(actual).isEqualTo(21)
    }
}
