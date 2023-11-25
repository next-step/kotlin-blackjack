package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BlackjackTest {

    @ParameterizedTest
    @MethodSource("cardsAndExpectSum")
    fun `카드의 합을 계산`(expect: Int, cards: List<String>) {
        val sut = Cards(*cards.toTypedArray())

        val actual = sut.sum()

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
    fun `최대 합이 21이 넘지 않는 경우 최대 합으로 반환`() {
        val cards = Cards("A", "10")

        val actual = cards.sum()

        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `최대 합이 21을 넘는 경우 최소 합으로 반환`() {
        val cards = Cards("A", "9", "J")

        val actual = cards.sum()

        assertThat(actual).isEqualTo(20)
    }

    companion object {
        @JvmStatic
        fun cardsAndExpectSum(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(15, listOf("7", "8")),
                Arguments.of(11, listOf("6", "5")),
                Arguments.of(15, listOf("6", "5", "4")),
            )
        }
    }
}
