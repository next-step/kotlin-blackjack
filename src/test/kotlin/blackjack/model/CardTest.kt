package blackjack.model

import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class CardTest {

    @ParameterizedTest
    @MethodSource("cardNumberSuitProvider")
    fun `알맞은 카드를 반환한다`(cardNumber: CardNumber, suit: Suit) {
        val result = Card.get(cardNumber, suit)

        assertThat(result).hasFieldOrPropertyWithValue("cardNumber", cardNumber)
        assertThat(result).hasFieldOrPropertyWithValue("suit", suit)
    }

    companion object {
        @JvmStatic
        fun cardNumberSuitProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        CardNumber.ACE, Suit.CLUB
                    )
                },
                Arguments {
                    arrayOf(
                        CardNumber.JACK, Suit.DIAMOND
                    )
                }
            )
        }
    }
}
