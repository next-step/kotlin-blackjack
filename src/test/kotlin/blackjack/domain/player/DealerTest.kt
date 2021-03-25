package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DealerTest {

    @ParameterizedTest
    @MethodSource("provideDealerCards")
    fun `딜러 카드의 점수기 16점 보다 높은 경우 카드를 더이상 가질 수 없다`(cards: Cards, expected: Boolean) {
        val dealer = Dealer("자손", cards)
        val result = dealer.isNotTakeable()
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideDealerCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Cards(
                        Card(Suit.HEART, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.SIX)
                    ),
                    false
                ),
                Arguments.of(
                    Cards(
                        Card(Suit.HEART, Denomination.JACK),
                        Card(Suit.HEART, Denomination.SEVEN)
                    ),
                    true
                )
            )
        }
    }
}
