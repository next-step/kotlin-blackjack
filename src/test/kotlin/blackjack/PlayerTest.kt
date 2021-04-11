package blackjack

import blackjack.card.Card
import blackjack.card.CardType
import blackjack.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PlayerTest {

    @ParameterizedTest
    @MethodSource("arguments")
    fun `합산 테스트`(cards: List<Card>, sumValue: Int) {
        val player = Player("A-name", cards)
        assertThat(player.getTotalSum()).isEqualTo(sumValue)
    }

    @ParameterizedTest
    @MethodSource("arguments2")
    fun `추가 카드 더 받는 테스트`(cards: List<Card>, result: Boolean) {
        val player = Player("A-name", cards)
        assertThat(player.isReceiveMoreCard()).isEqualTo(result)
    }

    companion object {
        @JvmStatic private fun arguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(Card(CardType.SPADE, CardValue.ONE), Card(CardType.SPADE, CardValue.KING)),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(CardType.SPADE, CardValue.ONE),
                        Card(CardType.SPADE, CardValue.KING),
                        Card(CardType.SPADE, CardValue.KING)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        Card(CardType.SPADE, CardValue.ONE),
                        Card(CardType.SPADE, CardValue.KING),
                        Card(CardType.SPADE, CardValue.EIGHT)
                    ),
                    19
                )
            )
        }

        @JvmStatic private fun arguments2(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(Card(CardType.SPADE, CardValue.ONE), Card(CardType.SPADE, CardValue.KING)),
                    false
                ),
                Arguments.of(
                    listOf(
                        Card(CardType.SPADE, CardValue.ONE),
                        Card(CardType.SPADE, CardValue.KING),
                        Card(CardType.SPADE, CardValue.KING)
                    ),
                    false
                ),
                Arguments.of(
                    listOf(
                        Card(CardType.SPADE, CardValue.ONE),
                        Card(CardType.SPADE, CardValue.KING),
                        Card(CardType.SPADE, CardValue.EIGHT)
                    ),
                    true
                )
            )
        }
    }
}
