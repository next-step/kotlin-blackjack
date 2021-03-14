package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    private val deck = blackjack {
        normal(2..10)
    }.build()

    @Test
    internal fun `딜러와 플레이어가 있다`() {
        val game = BlackJackGame(listOf(Player.Person("pobi"), Player.Person("json")), deck)
        assertThat(game.allPlayers())
            .hasSize(3)
            .contains(Dealer())
    }

    @Test
    internal fun `처음엔 두장씩 준다`() {
        val game = BlackJackGame(listOf(Player.Person("pobi"), Player.Person("json")), deck)
        game.prepareDraw()
        assertThat(game.players).allSatisfy {
            assertThat(it.cards.size).isEqualTo(2)
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `한장을 받는다`() {
        val pobi = Player.Person("pobi")
        val game = BlackJackGame(listOf(pobi), deck)
        assertThat(pobi.cards.size).isEqualTo(0)
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() }) { player: Player ->
            assertThat(player).isEqualTo(pobi)
            assertThat(player.cards.size).isEqualTo(1)
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `결과를 알 수 있다`() {
        val game = BlackJackGame(listOf(Player.Person("pobi")), deck)
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() })
        for (player in game.players) {
            assertThat(player.cards.size).isEqualTo(1)
            assertThat(player.score()).isNotZero()
        }
    }
}
