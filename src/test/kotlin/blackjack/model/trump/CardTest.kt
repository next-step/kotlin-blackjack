package blackjack.model.trump

import blackjack.model.player.MockDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class CardTest {
    private val deck = MockDeck()

    @ParameterizedTest
    @MethodSource("cardNumberSuitProvider")
    fun `알맞은 카드를 반환한다`(cardNumber: CardNumber, suit: Suit) {
        val result = deck.peekCard(cardNumber, suit)

        assertThat(result).hasFieldOrPropertyWithValue("cardNumber", cardNumber)
        assertThat(result).hasFieldOrPropertyWithValue("suit", suit)
    }

    @Test
    fun `각 카드는 종류별로 한 장만 뽑힐 수 있다`() {
        val deckOriginalSize = deck.size
        val deck = TrumpDeck()

        val totalDraw = (0 until deckOriginalSize).map { deck.draw() }.toSet()

        assertThat(totalDraw.size).isEqualTo(deckOriginalSize)
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
