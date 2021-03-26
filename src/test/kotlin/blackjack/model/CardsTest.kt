package blackjack.model

import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class CardsTest {
    @Test
    fun `Cards 기본 생성자를 사용하면 2개의 카드가 생성된다`() {
        val result = Cards.Builder().cards(Cards.firstDraw()).build()

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
                        Cards.Builder().cards(
                            listOf(
                                Card.get(CardNumber.ACE, Suit.DIAMOND),
                                Card.get(CardNumber.JACK, Suit.DIAMOND),
                                Card.get(CardNumber.TEN, Suit.DIAMOND)
                            )
                        ).build(),
                        Score(31)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards.Builder().cards(
                            listOf(
                                Card.get(CardNumber.ACE, Suit.DIAMOND),
                                Card.get(CardNumber.ACE, Suit.DIAMOND),
                                Card.get(CardNumber.ACE, Suit.DIAMOND)
                            )
                        ).build(),
                        Score(33)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards.Builder().cards(
                            listOf(
                                Card.get(CardNumber.THREE, Suit.DIAMOND),
                                Card.get(CardNumber.JACK, Suit.DIAMOND),
                                Card.get(CardNumber.TEN, Suit.DIAMOND)
                            )
                        ).build(),
                        Score(23)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards.Builder().cards(
                            listOf(
                                Card.get(CardNumber.ACE, Suit.DIAMOND),
                                Card.get(CardNumber.TEN, Suit.DIAMOND)
                            )
                        ).build(),
                        Score(21)
                    )
                }
            )
        }
    }
}
