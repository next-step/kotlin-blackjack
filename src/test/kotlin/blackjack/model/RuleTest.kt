package blackjack.model

import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RuleTest {
    @ParameterizedTest
    @MethodSource("scoreProvider")
    fun `21이하의 가능한 가장 큰 수를 리턴(불가능하면 가장 작은 수 리턴)`(cards: Cards, score: Score) {
        val result = Rule.getScore(cards)

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
                        Score(21)
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
                        Score(13)
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
