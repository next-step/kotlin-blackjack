package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class GamerTest {

    private val cards = Cards(listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK)))

    @Test
    fun `게이머 이름의 길이는 1글자 이상이다`() {
        assertDoesNotThrow { FakeGamer("1", cards) }
    }

    @Test
    fun `게이머 이름의 길이가 0 일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { FakeGamer("", cards) }
    }

    @Test
    fun `게이머의 이름이 공백일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { FakeGamer(" ", cards) }
    }
}
