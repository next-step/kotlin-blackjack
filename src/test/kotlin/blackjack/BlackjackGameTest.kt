package blackjack

import blackjack.PlayerTest.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    internal fun `플레이어가 있다`() {
        BlackJackGame(listOf(Player("pobi"), Player("json")))
    }

    @Test
    internal fun `처음엔 두장씩 준다`() {
        val prepare = BlackJackGame(listOf(Player("pobi"), Player("json")), Dealer())
            .prepare()
        prepare.draw()
        assertThat(listOf<Int>()).allSatisfy {
            assertThat(it.cards.size).isEqualTo(2)
        }
    }

    class BlackJackGame(private val players: List<Player>)
}
