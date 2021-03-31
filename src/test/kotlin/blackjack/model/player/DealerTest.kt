package blackjack.model.player

import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards
import blackjack.model.trump.TrumpDeck
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DealerTest {
    @ParameterizedTest
    @MethodSource("cardsProvider")
    fun `딜러는 처음 받은 2장의 카드 점수 합이 16이하면 한장 더 받는다`(cards: Cards, cardsSize: Int) {
        val dealer = Dealer(cards, deck)

        assertThat(dealer.cards.size).isEqualTo(cardsSize)
    }

    companion object {
        private val deck = TrumpDeck()

        @JvmStatic
        private fun cardsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Cards(listOf(deck.peekCard(CardNumber.EIGHT, Suit.CLUB), deck.peekCard(CardNumber.FIVE, Suit.HEART))),
                        3
                    )
                },
                Arguments {
                    arrayOf(
                        Cards(listOf(deck.peekCard(CardNumber.ACE, Suit.HEART), deck.peekCard(CardNumber.TEN, Suit.CLUB))),
                        2
                    )
                }
            )
        }
    }
}
