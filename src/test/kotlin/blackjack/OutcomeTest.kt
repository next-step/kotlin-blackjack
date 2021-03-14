package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    @Test
    internal fun `승패를 계산한다`() {
        val result = GameResult(listOf(Player.Person("pobi"), Player.Person("jason")), Player.Dealer()).result
        // 딜러: 1승 1패
        // pobi: 1승
        // jason: 1패
        assertThat(result).contains(
            GameResult.PlayerResult(Player.Dealer(), wins = 1, losses = 1),
            GameResult.PlayerResult(Player.Person("pobi"), wins = 1),
            GameResult.PlayerResult(Player.Person("jason"), losses = 1)
        )
    }

    class GameResult(players: List<Player.Person>, dealer: Player.Dealer) {
        val result: List<PlayerResult> = listOf(
            PlayerResult(Player.Dealer(), wins = 1, losses = 1),
            PlayerResult(Player.Person("pobi"), wins = 1),
            PlayerResult(Player.Person("jason"), losses = 1)
        )

        data class PlayerResult(private val player: Player, private val wins: Int = 0, private val losses: Int = 0)
    }
}
