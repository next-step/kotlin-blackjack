package blackjack.domain

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
    @CsvSource(value = ["KING, FIVE, 15", "JACK, THREE, 13", "QUEEN, EIGHT, 18"])
    fun `문자 카드는 10으로 계산`(number1: Number, number2: Number, expect: Int) {
        val sut = Cards(Card.diamond(number1), Card.diamond(number2))

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
                Arguments.of(15, listOf(Card.diamond(Number.SEVEN), Card.diamond(Number.EIGHT))),
                Arguments.of(11, listOf(Card.diamond(Number.SIX), Card.diamond(Number.FIVE))),
                Arguments.of(15, listOf(Card.diamond(Number.SIX), Card.diamond(Number.FIVE), Card.diamond(Number.FOUR))),
            )
        }

        @JvmStatic
        fun includeOneAceCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(21, listOf(Card.diamond(Number.ACE), Card.diamond(Number.TEN))),
                Arguments.of(20, listOf(Card.diamond(Number.ACE), Card.diamond(Number.NINE), Card.diamond(Number.JACK))),
            )
        }

        @JvmStatic
        fun includeMultipleAceCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(21,
                    listOf(
                        Card.diamond(Number.ACE), Card.diamond(Number.ACE),
                        Card.diamond(Number.ACE), Card.diamond(Number.EIGHT)
                    )
                ),
                Arguments.of(21,
                    listOf(
                        Card.diamond(Number.ACE), Card.diamond(Number.ACE), Card.diamond(Number.ACE),
                        Card.diamond(Number.TEN), Card.diamond(Number.EIGHT)
                    )
                ),
            )
        }
    }
}
