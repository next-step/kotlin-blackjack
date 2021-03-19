package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class BlackjackGameTest {
    private val deck = blackjack {
        normal(2..10)
    }.build()

    @Test
    internal fun `딜러와 플레이어가 있다`() {
        val players = Players(CardPlayer.Player("pobi"), CardPlayer.Player("json"))
        assertThat(players.allPlayers())
            .hasSize(3)
            .contains(CardPlayer.Dealer())
    }

    @Test
    internal fun `처음엔 두장씩 준다`() {
        val players = Players(CardPlayer.Player("pobi"), CardPlayer.Player("json"))
        val game = BlackJackGame(players, deck).prepareDraw()
        assertThat(players.allPlayers()).allSatisfy {
            assertThat(it.cards.size).isEqualTo(2)
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `한장을 받는다`() {
        val pobi = CardPlayer.Player("pobi")
        val game = BlackJackGame(Players(pobi), deck)
        assertThat(pobi.cards.size).isEqualTo(0)
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() }) { player: CardPlayer ->
            assertThat(player).isEqualTo(pobi)
            assertThat(player.cards.size).isEqualTo(1)
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `결과를 알 수 있다`() {
        val players = Players(CardPlayer.Player("pobi"))
        val game = BlackJackGame(players, deck)
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() })
        game.endDraw()

        for (player in players.allPlayers()) {
            assertThat(player.cards.size).isEqualTo(1)
            assertThat(player.score()).isNotZero()
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    fun `딜러가 마지막 카드를 받는다`() {
        val players = Players(CardPlayer.Player("pobi"))
        val game = BlackJackGame(players, deck)

        game.endDraw()

        assertThat(players.dealer.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러가 21을 초과하면 플레이어가 승리한다`() {
        val players = Players(CardPlayer.Player("pobi"))

        players.dealer.accept(Card("K", Symbol.HEARTS))
        players.dealer.accept(Card("K", Symbol.HEARTS))
        players.dealer.accept(Card("K", Symbol.HEARTS))

        val list = players.gameResult()
        list.find { it.name == "pobi" }?.let {
            assertThat(it.wins).isEqualTo(1)
        } ?: run { fail("포비가 우승해야 합니다") }

        list.find { it.name == players.dealer.name }?.let {
            assertThat(it.losses).isEqualTo(1)
        } ?: run { fail("딜러가 패배해야 합니다") }
    }
}
