package blackjack

import blackjack.CardTest.Card
import blackjack.CardTest.Symbol
import blackjack.PlayerTest.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    internal fun `플레이어가 있다`() {
        BlackJackGame(listOf(Player.Person("pobi"), Player.Person("json")))
    }

    @Test
    internal fun `처음엔 두장씩 준다`() {
        val game = BlackJackGame(listOf(Player.Person("pobi"), Player.Person("json")), Dealer())
        game.prepareDraw()
        assertThat(game.allPlayers()).allSatisfy {
            assertThat(it.cards.size).isEqualTo(2)
        }
    }

    @Test
    internal fun `한장을 받는다`() {
        val game = BlackJackGame(listOf(Player.Person("pobi"), Player.Person("json")), Dealer())
        val turn = game.nextTurn()
        assertThat(turn.result.player.cards.size).isEqualTo(0)
        turn.draw(true)
        assertThat(turn.result.player.cards.size).isEqualTo(1)
    }

    class Dealer(private val player: Player = Player.Person("dealer")) : Player by player

    class BlackJackGame(
        private val players: List<Player>,
        private val dealer: Dealer = Dealer()
    ) {
        fun prepareDraw() {
            repeat(2) {
                allPlayers().forEach { it.draw(Card("A", Symbol.CLUBS)) }
            }
        }

        fun allPlayers(): List<Player> = players + dealer
    }
}
