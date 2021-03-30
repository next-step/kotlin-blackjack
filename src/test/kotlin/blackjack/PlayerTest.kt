package blackjack

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class PlayerTest {

    @Test
    fun `점수가 21점을 넘으면 죽는다`() {
        val player = Player("kasha", 1)
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.TEN))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.JACK))
        player.cardDeck.add(Card(CardType.SPADE, CardNumber.QUEEN))

        assertTrue(player.isBust())
    }

    @Test
    fun `베팅 금액이 0보다 작을 경우 예외가 발생합니다`() {
        assertThrows<IllegalArgumentException> {
            Player("kasha", -1000)
        }
    }
}
