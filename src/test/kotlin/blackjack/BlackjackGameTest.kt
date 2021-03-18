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
        val game = BlackJackGame(listOf(CardPlayer.Player("pobi"), CardPlayer.Player("json")), deck)
        assertThat(game.allPlayers())
            .hasSize(3)
            .contains(CardPlayer.Dealer())
    }

    @Test
    internal fun `처음엔 두장씩 준다`() {
        val game = BlackJackGame(listOf(CardPlayer.Player("pobi"), CardPlayer.Player("json")), deck)
        game.prepareDraw()
        assertThat(game.allPlayers()).allSatisfy {
            assertThat(it.cards.size).isEqualTo(2)
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `한장을 받는다`() {
        val pobi = CardPlayer.Player("pobi")
        val game = BlackJackGame(listOf(pobi), deck)
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
        val game = BlackJackGame(listOf(CardPlayer.Player("pobi")), deck)
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() })
        game.endDraw()

        for (player in game.allPlayers()) {
            assertThat(player.cards.size).isEqualTo(1)
            assertThat(player.score()).isNotZero()
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    fun `딜러가 마지막 카드를 받는다`() {
        val game = BlackJackGame(listOf(CardPlayer.Player("pobi")), deck)

        game.endDraw()

        assertThat(game.dealer.cards.size).isEqualTo(1)
    }

    @Test
    fun `딜러가 21을 초과하면 플레이어가 승리한다`() {
        val game = BlackJackGame(listOf(CardPlayer.Player("pobi")), deck)

        game.dealer.accept(Card("K", Symbol.HEARTS))
        game.dealer.accept(Card("K", Symbol.HEARTS))
        game.dealer.accept(Card("K", Symbol.HEARTS))

        val list = game.players vs game.dealer
        list.find { it.name == "pobi" }?.let {
            assertThat(it.wins).isEqualTo(1)
        } ?: run { fail("포비가 우승해야 합니다") }

        list.find { it.name == "dealer" }?.let {
            assertThat(it.losses).isEqualTo(1)
        } ?: run { fail("딜러가 패배해야 합니다") }
    }
}
