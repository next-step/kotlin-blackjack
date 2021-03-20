package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {
    @Test
    fun `버스트된 플레이어는 카드를 추가하려고 할 때 예외가 발생한다`() {
        val player = Player.from(PlayerName("test"))
        player.addPlayerCard(Card.of(CardSuit.HEARTS, CardSpell.JACK))
        player.addPlayerCard(Card.of(CardSuit.CLUBS, CardSpell.JACK))
        player.addPlayerCard(Card.of(CardSuit.CLUBS, CardSpell.TWO))

        assertThrows<IllegalArgumentException> {
            player.addPlayerCard(Card.of(CardSuit.HEARTS, CardSpell.ACE))
        }

    }
}