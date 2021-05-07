package blackjack.model.gamer

import blackjack.model.MockDeck
import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Suit
import blackjack.model.trump.Deck
import blackjack.model.trump.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class DealerTest {
    @ParameterizedTest
    @MethodSource("cardsProvider")
    fun `딜러는 처음 받은 2장의 카드 점수 합이 16이하면 한장 더 받는다`(deck: Deck, cardsSize: Int) {
        val dealer = Dealer()
        repeat(Cards.INITIAL_DRAW_COUNT) {
            dealer.draw(deck)
        }

        dealer.drawIfNeeded(deck)

        assertThat(dealer.cards.size).isEqualTo(cardsSize)
    }

    companion object {
        @JvmStatic
        private fun cardsProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        MockDeck(Card(CardNumber.EIGHT, Suit.HEART), Card(CardNumber.FOUR, Suit.CLUB), Card(CardNumber.TWO, Suit.DIAMOND)),
                        3
                    )
                },
                Arguments {
                    arrayOf(
                        MockDeck(Card(CardNumber.ACE, Suit.HEART), Card(CardNumber.TEN, Suit.CLUB), Card(CardNumber.TWO, Suit.DIAMOND)),
                        2
                    )
                }
            )
        }
    }
}
