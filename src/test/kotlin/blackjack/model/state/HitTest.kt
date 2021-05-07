package blackjack.model.state

import blackjack.model.trump.Card
import blackjack.model.trump.CardNumber
import blackjack.model.trump.Cards
import blackjack.model.trump.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HitTest {
    @ParameterizedTest
    @MethodSource("hitCardProvider")
    fun `카드를 추가해서 점수가 valid 하면 Hit, 아니면 Bust 이다`(card: Card, isHit: Boolean) {
        val currentState = Hit(Cards(Card(CardNumber.FIVE, Suit.CLUB), Card(CardNumber.KING, Suit.SPADE)))

        val result = currentState.add(card)

        assertThat(result is Hit).isEqualTo(isHit)
        assertThat(result is Bust).isEqualTo(!isHit)
    }

    companion object {
        @JvmStatic
        fun hitCardProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Card(CardNumber.FOUR, Suit.CLUB),
                        true
                    )
                },
                Arguments {
                    arrayOf(
                        Card(CardNumber.ACE, Suit.CLUB),
                        true
                    )
                },
                Arguments {
                    arrayOf(
                        Card(CardNumber.EIGHT, Suit.CLUB),
                        false
                    )
                }
            )
        }
    }
}
