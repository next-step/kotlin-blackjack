package blackjack.model

import blackjack.model.score.Score
import blackjack.model.trump.Card
import blackjack.model.trump.Cards
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RuleTest {
    @ParameterizedTest
    @MethodSource("scoreProvider")
    fun `21이하의 가능한 가장 큰 수를 리턴(불가능하면 가장 작은 수 리턴)`(cards: Cards, score: Score) {
        val result = BlackJackRule.getScore(cards)

        assertThat(result).isEqualTo(score)
    }

    companion object {
        private val deck = MockDeck()

        @JvmStatic
        fun scoreProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Cards(
                            Card(CardNumber.ACE, Suit.DIAMOND),
                            Card(CardNumber.JACK, Suit.DIAMOND),
                            Card(CardNumber.TEN, Suit.DIAMOND)
                        ),
                        Score(21)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards(
                            Card(CardNumber.ACE, Suit.CLUB),
                            Card(CardNumber.ACE, Suit.HEART)
                        ),
                        Score(12)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards(
                            Card(CardNumber.THREE, Suit.SPADE),
                            Card(CardNumber.JACK, Suit.SPADE),
                            Card(CardNumber.TEN, Suit.SPADE)
                        ),
                        Score.ZERO
                    )
                },
                Arguments {
                    arrayOf(
                        Cards(
                            Card(CardNumber.ACE, Suit.SPADE),
                            Card(CardNumber.TEN, Suit.HEART)
                        ),
                        Score(21)
                    )
                }
            )
        }
    }
}
