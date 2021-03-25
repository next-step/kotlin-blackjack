package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PlayerTest {

    private val cards = Cards(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK))

    @Test
    fun `이름이 같을 경우 같은 플레이어로 취급한다`() {
        assertThat(Player("자손", cards)).isEqualTo(Player("자손", cards))
    }

    @ParameterizedTest
    @MethodSource("provideDealerCards")
    fun `딜러 카드의 점수기 16점 보다 높은 경우 카드를 더이상 가질 수 없다`(cards: Cards, expected: Boolean) {
        val player = Player("자손", cards)
        val result = player.isTakeableDealer()
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `플레이어 카드의 점수가 블랙잭(21점)보다 높거나 같은 경우 카드를 더이상 가질 수 없다`(cards: Cards, expected: Boolean) {
        val player = Player("자손", cards)
        val result = player.isNotTakeable()
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    makeCards(
                        Card(Suit.HEART, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.JACK)
                    ),
                    false
                ),
                Arguments.of(
                    makeCards(
                        Card(Suit.HEART, Denomination.ACE),
                        Card(Suit.HEART, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.JACK)
                    ),
                    true
                ),
                Arguments.of(
                    makeCards(
                        Card(Suit.HEART, Denomination.TWO),
                        Card(Suit.HEART, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.JACK)
                    ),
                    true
                )
            )
        }

        @JvmStatic
        private fun provideDealerCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Cards(
                        Card(Suit.HEART, Denomination.JACK),
                        Card(Suit.DIAMOND, Denomination.SIX)
                    ),
                    true
                ),
                Arguments.of(
                    Cards(
                        Card(Suit.HEART, Denomination.JACK),
                        Card(Suit.HEART, Denomination.SEVEN)
                    ),
                    false
                )
            )
        }

        private fun makeCards(vararg cards: Card): Cards {
            val cardInitSize = 2

            val initCards = cards.take(cardInitSize)
            val restCards = cards.drop(cardInitSize)

            return Cards(*initCards.toTypedArray()).apply {
                restCards.forEach(this::add)
            }
        }
    }
}
