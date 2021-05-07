package blackjack.model.trump

import blackjack.model.score.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class CardsTest {
    @Test
    fun `Cards 기본 생성자를 사용하면 2개의 카드가 생성된다`() {
        val result = Cards.firstDraw(TrumpDeck())

        assertThat(result.size).isEqualTo(2)
    }

    @ParameterizedTest
    @MethodSource("scoreProvider")
    fun `getHighestScore 가능한 가장 큰 점수를 리턴한다`(cards: Cards, score: Score) {
        val result = cards.getHighestScore()

        assertThat(result).isEqualTo(score)
    }

    companion object {
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
                        Score(31)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards(
                            Card(CardNumber.ACE, Suit.SPADE),
                            Card(CardNumber.ACE, Suit.CLUB)
                        ),
                        Score(22)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards(
                            Card(CardNumber.THREE, Suit.SPADE),
                            Card(CardNumber.JACK, Suit.CLUB),
                            Card(CardNumber.TEN, Suit.HEART)
                        ),
                        Score(23)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards(
                            Card(CardNumber.TEN, Suit.CLUB),
                            Card(CardNumber.ACE, Suit.HEART)
                        ),
                        Score(21)
                    )
                }
            )
        }
    }
}
