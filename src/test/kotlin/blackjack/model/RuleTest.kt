package blackjack.model

import blackjack.model.player.MockDeck
import blackjack.model.score.Score
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
        val result = TrumpRule.getScore(cards)

        assertThat(result).isEqualTo(score)
    }

    companion object {
        private val deck = MockDeck()

        @JvmStatic
        fun scoreProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Cards.Builder().cards(
                            listOf(
                                deck.peekCard(CardNumber.ACE, Suit.DIAMOND),
                                deck.peekCard(CardNumber.JACK, Suit.DIAMOND),
                                deck.peekCard(CardNumber.TEN, Suit.DIAMOND)
                            )
                        ).build(),
                        Score(21)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards.Builder().cards(
                            listOf(
                                deck.peekCard(CardNumber.ACE, Suit.CLUB),
                                deck.peekCard(CardNumber.ACE, Suit.HEART)
                            )
                        ).build(),
                        Score(12)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards.Builder().cards(
                            listOf(
                                deck.peekCard(CardNumber.THREE, Suit.SPADE),
                                deck.peekCard(CardNumber.JACK, Suit.SPADE),
                                deck.peekCard(CardNumber.TEN, Suit.SPADE)
                            )
                        ).build(),
                        Score(23)
                    )
                },
                Arguments {
                    arrayOf(
                        Cards.Builder().cards(
                            listOf(
                                deck.peekCard(CardNumber.ACE, Suit.SPADE),
                                deck.peekCard(CardNumber.TEN, Suit.HEART)
                            )
                        ).build(),
                        Score(21)
                    )
                }
            )
        }
    }
}
