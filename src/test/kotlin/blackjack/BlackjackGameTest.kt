package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    private val deck = blackjack {
        normal(2..10)
    }.build()

    @Test
    internal fun `딜러와 플레이어가 있다`() {
        val players = listOf(CardPlayer.Player("pobi"), CardPlayer.Player("json"))

        assertThat(Players(players).allPlayers())
            .hasSize(3)
            .containsAll(players + CardPlayer.Dealer())
    }

    @Test
    internal fun `처음엔 두장씩 준다`() {
        val players = Players(CardPlayer.Player("pobi"), CardPlayer.Player("json"))
        BlackJackGame(players, deck).prepareDraw()

        assertThat(players.allPlayers()).allSatisfy {
            assertThat(it.cards).hasSize(2)
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `한장을 받는다`() {
        val pobi = CardPlayer.Player("pobi")
        val game = BlackJackGame(Players(pobi), deck)
        val answer = mutableListOf(true, false)

        var invokecount = 0
        game.draw({ answer.removeFirst() }) {
            assertThat(it).isEqualTo(pobi)
            assertThat(it.cards).hasSize(1)
            invokecount++
        }
        assertThat(invokecount).isEqualTo(1)
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `결과를 알 수 있다`() {
        val players = Players(CardPlayer.Player("pobi"))
        val game = BlackJackGame(players, deck)
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() })
        game.endDraw()

        assertThat(players).allSatisfy {
            assertThat(it.cards).hasSize(1)
            assertThat(it.score()).isNotZero()
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    fun `딜러가 마지막 카드를 받는다`() {
        val dealer = CardPlayer.Dealer()
        BlackJackGame(Players(dealer, CardPlayer.Player("pobi")), deck).endDraw()

        assertThat(dealer.cards).hasSize(1)
    }

    @Test
    fun `딜러가 21을 초과하면 플레이어가 승리한다`() {
        val dealer = CardPlayer.Dealer()
        val players = Players(dealer, CardPlayer.Player("pobi"))

        dealer.apply {
            accept(Card("K", Symbol.HEARTS))
            accept(Card("K", Symbol.HEARTS))
            accept(Card("K", Symbol.HEARTS))
        }

        val list = players.gameResult()
        assertThat(list)
            .hasSize(2)
            .filteredOnAssertions { assertThat(it).isEqualTo(PlayerResult(players.first(), wins = 1)) }
            .hasSize(1)

        assertThat(list)
            .filteredOnAssertions { assertThat(it).isEqualTo(PlayerResult(dealer, losses = 1)) }
            .hasSize(1)
    }
}
