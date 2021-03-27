package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
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

    @Test
    fun `플레이어 이름의 길이는 1글자 이상이다`() {
        assertDoesNotThrow { Player("1", cards) }
    }

    @Test
    fun `플레이어 이름의 길이가 0 일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player("", cards) }
    }

    @Test
    fun `플레이어의 이름이 공백일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player(" ", cards) }
    }

    @Test
    fun `이름이 같을 경우 같은 플레이어로 취급한다`() {
        assertThat(Player("자손", cards)).isEqualTo(Player("자손", cards))
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `플레이어 카드의 점수가 블랙잭(21점)보다 높거나 같은 경우 카드를 더이상 가질 수 없다`(cards: Cards, expected: Boolean) {
        val player = Player("자손", cards)
        val result = player.isTakeable()
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        private fun provideCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.JACK),
                            Card(Suit.DIAMOND, Denomination.JACK)
                        )
                    ),
                    true
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.ACE),
                            Card(Suit.HEART, Denomination.JACK),
                            Card(Suit.DIAMOND, Denomination.JACK)
                        )
                    ),
                    false
                ),
                Arguments.of(
                    Cards(
                        listOf(
                            Card(Suit.HEART, Denomination.TWO),
                            Card(Suit.HEART, Denomination.JACK),
                            Card(Suit.DIAMOND, Denomination.JACK)
                        )
                    ),
                    false
                )
            )
        }
    }
}
