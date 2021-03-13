package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    internal fun `플레이어가 있다`() {
        BlackJackGame(listOf(Player.Person("pobi"), Player.Person("json")))
    }

    @Test
    internal fun `처음엔 두장씩 준다`() {
        val game = BlackJackGame(listOf(Player.Person("pobi"), Player.Person("json")))
        game.prepareDraw()
        assertThat(game.players).allSatisfy {
            assertThat(it.cards.size).isEqualTo(2)
        }
    }

    @Test
    internal fun `한장을 받는다`() {
        val pobi = Player.Person("pobi")
        val game = BlackJackGame(listOf(pobi))
        assertThat(pobi.cards.size).isEqualTo(0)
        game.draw({ true }) { player: Player ->
            assertThat(player).isEqualTo(pobi)
            assertThat(player.cards.size).isEqualTo(1)
        }
    }

    @Test
    internal fun `결과를 알 수 있다`() {
        val game = BlackJackGame(listOf(Player.Person("pobi")))
        game.draw({ true })
        for (player in game.players) {
            assertThat(player.cards.size).isEqualTo(1)
            assertThat(player.score()).isNotZero()
        }
    }
}
