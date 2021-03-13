package blackjack

import blackjack.PlayerTest.Player
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    internal fun `플레이어가 있다`() {
        BlackJackGame(listOf(Player("pobi"), Player("json")))
    }

    class BlackJackGame(private val players: List<Player>)
}
