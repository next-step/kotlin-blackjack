package blackjack.model.state

import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class InitialTest {
    @ParameterizedTest
    @MethodSource("firstDrawBlackJackProvider")
    fun `첫 드로우에 A + 10 이면 BlackJack 이고, 아니면 Hit 이다`(firstCard: Card, secondCard: Card, isBlackJack: Boolean) {
        val result = Initial().add(firstCard).add(secondCard)

        assertThat(result is BlackJack).isEqualTo(isBlackJack)
        assertThat(result is Hit).isEqualTo(!isBlackJack)
    }

    companion object {
        @JvmStatic
        fun firstDrawBlackJackProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Card(CardNumber.ACE, Suit.CLUB),
                        Card(CardNumber.KING, Suit.DIAMOND),
                        true
                    )
                },
                Arguments {
                    arrayOf(
                        Card(CardNumber.ACE, Suit.CLUB),
                        Card(CardNumber.TEN, Suit.DIAMOND),
                        true
                    )
                },
                Arguments {
                    arrayOf(
                        Card(CardNumber.ACE, Suit.CLUB),
                        Card(CardNumber.NINE, Suit.DIAMOND),
                        false
                    )
                }
            )
        }
    }
}
