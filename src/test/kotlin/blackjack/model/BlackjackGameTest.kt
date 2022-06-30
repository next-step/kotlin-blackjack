package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `게임이 종료되었는지 확인한다`() {
        val playerList = listOf(Player("jason", Cards(listOf(Card(CardNumber.Two, Suit.Heart)))))
        val game = BlackjackGame(Players(playerList))
        Assertions.assertThat(game.isGameOver()).isEqualTo(false)

        val playerList2 = listOf(Player("jason", Cards(listOf(Card(CardNumber.Two, Suit.Heart))), true))
        val game2 = BlackjackGame(Players(playerList2))
        Assertions.assertThat(game2.isGameOver()).isEqualTo(true)
    }

    @Test
    fun `한번의 턴을 진행한다`() {
        val playerList = listOf(Player("jason"))
        val game = BlackjackGame(Players(playerList))

        Assertions.assertThat(game.isGameOver()).isEqualTo(false)
        Assertions.assertThat(game.players.values[0].cards.values.size).isEqualTo(2)
        game.playTurn { true }
        Assertions.assertThat(game.players.values[0].cards.values.size).isEqualTo(3)

        game.playTurn { false }
        Assertions.assertThat(game.players.values[0].cards.values.size).isEqualTo(3)
        Assertions.assertThat(game.isGameOver()).isEqualTo(true)
    }

    @Test
    fun `딜러가 한번 턴을 진행한다`() {
        val playerList = listOf(Player("jason"))
        val game = BlackjackGame(Players(playerList))

        Assertions.assertThat(game.dealer.cards.values.size).isEqualTo(2)
        game.playDealer()
        Assertions.assertThat(game.dealer.cards.values.size).isEqualTo(3)
    }

    @Test
    fun `Results를 만든다`() {
        val playerList = listOf(Player("jason"), Player("pobi"))
        val game = BlackjackGame(Players(playerList))

        val results = game.createResults()
        Assertions.assertThat(results.playerResults.size).isEqualTo(2)
        Assertions.assertThat(results.dealerResult.win + results.dealerResult.lose).isEqualTo(2)
    }
}
