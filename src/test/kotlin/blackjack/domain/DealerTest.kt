package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DealerTest {
    @ParameterizedTest
    @MethodSource
    fun calculateDealerStatus(hand: Hand, status: Status) {
        assertThat(Dealer(hand).status).isEqualTo(status)
    }

    companion object {
        @JvmStatic
        fun calculateDealerStatus(): List<Arguments> {
            return listOf(
                Arguments.arguments(
                    Hand(
                        mutableListOf(
                            Card(CardNumber.ACE, CardSuit.CLOVER),
                            Card(CardNumber.JACK, CardSuit.CLOVER)
                        )
                    ),
                    Status.BLACKJACK
                ),
                Arguments.arguments(
                    Hand(
                        mutableListOf(
                            Card(CardNumber.JACK, CardSuit.CLOVER),
                            Card(CardNumber.JACK, CardSuit.CLOVER),
                            Card(CardNumber.ACE, CardSuit.CLOVER)
                        )
                    ),
                    Status.STAY
                ),
                Arguments.arguments(
                    Hand(
                        mutableListOf(
                            Card(CardNumber.ACE, CardSuit.CLOVER),
                            Card(CardNumber.SIX, CardSuit.CLOVER)
                        )
                    ),
                    Status.STAY
                ),
                Arguments.arguments(
                    Hand(
                        mutableListOf(
                            Card(CardNumber.ACE, CardSuit.CLOVER),
                            Card(CardNumber.FIVE, CardSuit.CLOVER)
                        )
                    ),
                    Status.HIT
                ),
                Arguments.arguments(
                    Hand(
                        mutableListOf(
                            Card(CardNumber.TWO, CardSuit.CLOVER),
                            Card(CardNumber.JACK, CardSuit.CLOVER),
                            Card(CardNumber.KING, CardSuit.CLOVER)
                        )
                    ),
                    Status.BUST
                )
            )
        }
    }
}
