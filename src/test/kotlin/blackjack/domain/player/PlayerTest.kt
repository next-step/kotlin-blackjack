package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.DummyState
import blackjack.domain.state.Hit
import blackjack.domain.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PlayerTest {

    private val cards = Cards(listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK)))
    private val dummyState = DummyState(cards)

    @Test
    fun `플레이어 이름의 길이는 1글자 이상이다`() {
        assertDoesNotThrow { Player("1", dummyState) }
    }

    @Test
    fun `플레이어 이름의 길이가 0 일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player("", dummyState) }
    }

    @Test
    fun `플레이어의 이름이 공백일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player(" ", dummyState) }
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `플레이어 카드의 상태가 히트가 아닌 경우 카드를 더이상 가질 수 없다`(state: State, expected: Boolean) {
        val player = Player("자손", state)
        val result = player.isTakeable()
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Hit(
                        Cards(
                            listOf(
                                Card(Suit.HEART, Denomination.JACK),
                                Card(Suit.DIAMOND, Denomination.JACK)
                            )
                        )
                    ),
                    true
                ),
                Arguments.of(
                    Blackjack(
                        Cards(
                            listOf(
                                Card(Suit.HEART, Denomination.ACE),
                                Card(Suit.HEART, Denomination.JACK),
                                Card(Suit.DIAMOND, Denomination.JACK)
                            )
                        )
                    ),
                    false
                ),
                Arguments.of(
                    Bust(
                        Cards(
                            listOf(
                                Card(Suit.HEART, Denomination.TWO),
                                Card(Suit.HEART, Denomination.JACK),
                                Card(Suit.DIAMOND, Denomination.JACK)
                            )
                        )
                    ),
                    false
                )
            )
        }
    }
}
