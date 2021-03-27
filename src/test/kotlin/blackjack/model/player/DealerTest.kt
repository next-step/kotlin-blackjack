package blackjack.model.player

import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DealerTest {
    @ParameterizedTest
    @MethodSource("cardsProvider")
    fun `딜러는 처음 받은 2장의 카드 점수 합이 16이하면 한장 더 받는다`(cards: Cards, cardsSize: Int) {
        val dealer = Dealer(cards)

        assertThat(dealer.cards.size).isEqualTo(cardsSize)
    }

    companion object {
        @JvmStatic
        private fun cardsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Cards.Builder()
                            .cards(listOf(Card.get(CardNumber.EIGHT, Suit.CLUB), Card.get(CardNumber.FIVE, Suit.HEART)))
                            .build(),
                        3
                    )
                },
                Arguments {
                    arrayOf(
                        Cards.Builder()
                            .cards(listOf(Card.get(CardNumber.ACE, Suit.HEART), Card.get(CardNumber.TEN, Suit.CLUB)))
                            .build(),
                        2
                    )
                }
            )
        }
    }
}
