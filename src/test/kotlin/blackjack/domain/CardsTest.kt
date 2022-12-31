package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardsTest {

    @ParameterizedTest
    @MethodSource("cardsCalculateScoreTest")
    fun `카드의 총 점수를 계산한다`(cards: Cards, result: Int) {
        // when
        val actual = cards.calculateScore()

        // then
        assertThat(actual).isEqualTo(result)
    }

    companion object {
        @JvmStatic
        fun cardsCalculateScoreTest() = Stream.of(
            Arguments.of(
                Cards(
                    listOf(
                        Card.of(Denomination.TWO),
                        Card.of(Denomination.THREE)
                    )
                ),
                5
            ),
            Arguments.of(
                Cards(
                    listOf(
                        Card.of(Denomination.ACE),
                        Card.of(Denomination.THREE),
                        Card.of(Denomination.FIVE)
                    )
                ),
                19
            ),
            Arguments.of(
                Cards(
                    listOf(
                        Card.of(Denomination.TWO),
                        Card.of(Denomination.JACK)
                    )
                ),
                12
            ),
            Arguments.of(
                Cards(
                    listOf(
                        Card.of(Denomination.ACE),
                        Card.of(Denomination.ACE)
                    )
                ),
                12
            )
        )
    }
}
