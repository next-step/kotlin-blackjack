package blackjack.domain

import blackjack.vo.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HandScoreStrategyTest {

    @MethodSource
    @ParameterizedTest
    fun calculate(cards: MutableList<Card>, expectedScore: Int) {
        assertThat(AceDifferScoreCalculateStrategy.calculate(Hand(cards))).isEqualTo(Score(expectedScore))
    }

    companion object {
        @JvmStatic
        fun calculate(): List<Arguments> {
            return listOf(
                Arguments.arguments(
                    mutableListOf(
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER)
                    ),
                    12
                ),
                Arguments.arguments(
                    mutableListOf(
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                    ),
                    12
                ),
                Arguments.arguments(
                    mutableListOf(
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.TEN, CardSuit.CLOVER)
                    ),
                    21
                ),
                Arguments.arguments(
                    mutableListOf(
                        Card(CardNumber.ACE, CardSuit.CLOVER),
                        Card(CardNumber.NINE, CardSuit.CLOVER)
                    ),
                    20
                ),
                Arguments.arguments(
                    mutableListOf(
                        Card(CardNumber.JACK, CardSuit.CLOVER),
                        Card(CardNumber.QUEEN, CardSuit.CLOVER),
                        Card(CardNumber.KING, CardSuit.CLOVER),
                    ),
                    30
                )
            )
        }
    }
}
