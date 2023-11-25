package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsTest {

    @ParameterizedTest
    @MethodSource("cardsAndExpectSum")
    fun `카드의 합을 계산`(expect: Int, cards: List<Card>) {
        val sut = Cards(*cards.toTypedArray())

        val actual = sut.sum()

        assertThat(actual).isEqualTo(expect)
    }

    @ParameterizedTest
    @CsvSource(value = ["K, 5, 15", "J, 3, 13", "Q, 8, 18"])
    fun `문자 카드는 10으로 계산`(card1: String, card2: String, expect: Int) {
        val sut = Cards(Card(card1), Card(card2))

        val actual = sut.sum()

        assertThat(actual).isEqualTo(expect)
    }

    @ParameterizedTest
    @MethodSource("includeOneAceCards")
    fun `Ace가 한장인 경우 최적의 값으로 합산`(expect: Int, cards: List<Card>) {
        val sut = Cards(*cards.toTypedArray())

        val actual = sut.sum()

        assertThat(actual).isEqualTo(expect)
    }

    @ParameterizedTest
    @MethodSource("includeMultipleAceCards")
    fun `Ace가 여러장인 경우 최적의 값으로 합산`(expect: Int, cards: List<Card>) {
        val sut = Cards(*cards.toTypedArray())

        val actual = sut.sum()

        assertThat(actual).isEqualTo(expect)
    }

    companion object {
        @JvmStatic
        fun cardsAndExpectSum(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(15, listOf(Card("7"), Card("8"))),
                Arguments.of(11, listOf(Card("6"), Card("5"))),
                Arguments.of(15, listOf(Card("6"), Card("5"), Card("4"))),
            )
        }

        @JvmStatic
        fun includeOneAceCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(21, listOf(Card("A"), Card("10"))),
                Arguments.of(20, listOf(Card("A"), Card("9"), Card("J"))),
            )
        }

        @JvmStatic
        fun includeMultipleAceCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(21,
                    listOf(
                        Card("A"), Card("A"),
                        Card("A"), Card("8")
                    )
                ),
                Arguments.of(21,
                    listOf(
                        Card("A"), Card("A"), Card("A"),
                        Card("10"), Card("8")
                    )
                ),
            )
        }
    }
}
