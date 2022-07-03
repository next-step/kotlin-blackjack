package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameResultTest {
    private val players = listOf(
        BLACKJACK_PLAYER(),
        HIT_PLAYER_SUM6(),
        HIT_PLYAER_SUM14(),
        HIT_PLAYER_SUM20(),
        BUST_PLAYER()
    )

    @Test
    internal fun `딜러가 블랙잭이면 플레이어는 무승부이거나 진다`() {
        val dealer = Dealer()
        SUM21_CARD2.map { dealer.receiveCard(it) }

        val gamePlayers = GamePlayers(players, dealer)

        val results = GameResult.of(gamePlayers)

        assertThat(results).hasSize(players.size)
        assertThat(results.filter { it.result == Result.WIN }).hasSize(0)
        assertThat(results.filter { it.result == Result.LOOSE }).hasSize(4)
        assertThat(results.filter { it.result == Result.DRAW }).hasSize(1)
    }

    @Test
    internal fun `딜러가 버스트이면 플레이어는 이기거나 진다`() {
        val dealer = Dealer()
        OVER_21_CARDS.map { dealer.receiveCard(it) }

        val gamePlayers = GamePlayers(players, dealer)

        val results = GameResult.of(gamePlayers)

        assertThat(results).hasSize(players.size)
        assertThat(results.filter { it.result == Result.WIN }).hasSize(4)
        assertThat(results.filter { it.result == Result.LOOSE }).hasSize(1)
        assertThat(results.filter { it.result == Result.DRAW }).hasSize(0)
    }

    @Test
    internal fun `딜러가 힛이면 플레이어는 이기거나 무승부이거나 질 수 있다`() {
        val dealer = Dealer()
        listOf(
            SPADE_TWO,
            HEART_TWO,
            DIAMOND_JACK
        ).map { dealer.receiveCard(it) }

        val gamePlayers = GamePlayers(players, dealer)

        val results = GameResult.of(gamePlayers)

        assertThat(results).hasSize(players.size)
        assertThat(results.filter { it.result == Result.WIN }).hasSize(2)
        assertThat(results.filter { it.result == Result.LOOSE }).hasSize(2)
        assertThat(results.filter { it.result == Result.DRAW }).hasSize(1)
    }
}
