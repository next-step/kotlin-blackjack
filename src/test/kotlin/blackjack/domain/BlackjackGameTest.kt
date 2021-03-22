package blackjack.domain

import blackjack.dealerWith
import blackjack.scoreIs
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    private val deck = deck {
        normal(2..10)
    }.build()

    @Test
    internal fun `Players 일급 컬렉션은 딜러를 포함한 목록을 제공한다`() {
        val players = listOf(CardPlayer.Player("pobi"), CardPlayer.Player("json"))

        assertThat(Players(players).allPlayers())
            .hasSize(3)
            .containsAll(players + CardPlayer.Dealer())
    }

    @Test
    internal fun `초기분배는 카드 두장을 받는다`() {
        val players = Players(CardPlayer.Player("pobi"), CardPlayer.Player("json"))
        BlackJackGame(players, deck).prepareDraw()

        assertThat(players.allPlayers()).allSatisfy {
            assertThat(it.hand).hasSize(2)
        }
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `카드를 받는다고 하면 한장을 받는다`() {
        val pobi = CardPlayer.Player("pobi")
        val game = BlackJackGame(Players(pobi), deck)

        var invokecount = 0
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() }) {
            assertThat(it).isEqualTo(pobi)
            assertThat(it.hand).hasSize(1)
            invokecount++
        }
        assertThat(invokecount).isEqualTo(1)
    }

    @Test
    @OptIn(ExperimentalStdlibApi::class)
    internal fun `받은 카드에 대해 점수가 존재한다`() {
        val players = Players(CardPlayer.Player("pobi"))
        val game = BlackJackGame(players, deck)
        val answer = mutableListOf(true, false)
        game.draw({ answer.removeFirst() })

        assertThat(players).allSatisfy {
            assertThat(it.hand).hasSize(1)
            assertThat(it.score()).isNotZero()
        }
    }

    @Test
    fun `종료시 딜러 카드가 16 이하이면 카드 한장을 더 받는다`() {
        val dealer = dealerWith("10", "6") {
            it scoreIs 16
        }
        BlackJackGame(Players(dealer), deck).endDraw()

        assertThat(dealer.hand).hasSize(3)
    }

    @Test
    fun `종료시 딜러 카드가 17 이상이면 더 받지 않는다`() {
        val dealer = dealerWith("10", "7") {
            it scoreIs 17
        }
        BlackJackGame(Players(dealer), deck).endDraw()

        assertThat(dealer.hand).hasSize(2)
    }
}
