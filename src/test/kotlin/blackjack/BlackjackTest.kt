package blackjack

import org.assertj.core.api.Assertions.assertThat
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
        val sut = Cards(card1, card2)

        val actual = sut.sum()

        assertThat(actual).isEqualTo(expect)
    }

    @ParameterizedTest
    @MethodSource("includeOneAceCards")
    fun `Ace가 한장인 경우 최적의 값으로 합산`(expect: Int, cards: List<String>) {
        val sut = Cards(*cards.toTypedArray())

        val actual = sut.sum()

        assertThat(actual).isEqualTo(expect)
    }

    @ParameterizedTest
    @MethodSource("includeMultipleAceCards")
    fun `Ace가 여러장인 경우 최적의 값으로 합산`(expect: Int, cards: List<String>) {
        val sut = Cards(*cards.toTypedArray())

        val actual = sut.sum()

        assertThat(actual).isEqualTo(expect)
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

        @JvmStatic
        fun includeOneAceCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(21, listOf("A", "10")),
                Arguments.of(20, listOf("A", "9", "J")),
            )
        }

        @JvmStatic
        fun includeMultipleAceCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(21, listOf("A", "A", "A", "8")),
                Arguments.of(21, listOf("A", "A", "A", "10", "8")),
            )
        }
    }
}
