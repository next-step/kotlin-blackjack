package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DealerTest {

    @ParameterizedTest
    @MethodSource("provideDealerCards")
    fun `딜러 카드의 점수가 16점 보다 높은 경우 카드를 더이상 가질 수 없다`(state: State, expected: Boolean) {
        val dealer = Dealer("자손", state)
        val result = dealer.isTakeable()
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideDealerCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Hit(
                        Cards(
                            listOf(
                                Card(Suit.HEART, Denomination.JACK),
                                Card(Suit.DIAMOND, Denomination.SIX)
                            )
                        )
                    ),
                    true
                ),
                Arguments.of(
                    Hit(
                        Cards(
                            listOf(
                                Card(Suit.HEART, Denomination.JACK),
                                Card(Suit.HEART, Denomination.SEVEN)
                            )
                        )
                    ),
                    false
                ),
                Arguments.of(
                    Bust(
                        Cards(
                            listOf(
                                Card(Suit.HEART, Denomination.JACK),
                                Card(Suit.HEART, Denomination.QUEEN),
                                Card(Suit.HEART, Denomination.KING)
                            )
                        )
                    ),
                    false
                )
            )
        }
    }
}
