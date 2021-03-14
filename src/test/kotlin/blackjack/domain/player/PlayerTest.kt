package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {

    private val cards = Cards(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK))

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
}
