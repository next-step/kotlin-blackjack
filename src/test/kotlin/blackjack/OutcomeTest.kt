package blackjack

import blackjack.Player.Person.PlayResult
import blackjack.Player.Person.PlayResult.DRAWS
import blackjack.Player.Person.PlayResult.LOSSES
import blackjack.Player.Person.PlayResult.WIN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    @Test
    internal fun `승패를 계산한다`() {
        val pobi = Player.Person("pobi")
            .apply {
                accept(Card("A", Symbol.DIAMONDS))
                accept(Card("K", Symbol.DIAMONDS))
            }
        val jason = Player.Person("jason")
            .apply {
                accept(Card("K", Symbol.DIAMONDS))
                accept(Card("2", Symbol.DIAMONDS))
            }
        val dealer = Player.Dealer()
            .apply {
                accept(Card("K", Symbol.DIAMONDS))
                accept(Card("K", Symbol.DIAMONDS))
            }

        val result = GameResult(listOf(pobi, jason), dealer).result()

        assertThat(result).contains(
            GameResult.PlayerResult(dealer, wins = 1, losses = 1),
            GameResult.PlayerResult(pobi, wins = 1),
            GameResult.PlayerResult(jason, losses = 1)
        )
    }

    class GameResult(private val players: List<Player.Person>, private val dealer: Player.Dealer) {
        fun result(): List<PlayerResult> {
            return players.map { it to (it vs dealer) }
                .map { (player, playResult) ->
                    PlayerResult(player)
                        .apply {
                            update(playResult)
                        }
                } + PlayerResult(dealer)
                .apply {
                    for (player in players) {
                        update(dealer vs player)
                    }
                }
        }

        class PlayerResult(
            private val player: Player,
            var wins: Int = 0,
            var losses: Int = 0,
            var draws: Int = 0
        ) {

            fun update(playResult: PlayResult) {
                when (playResult) {
                    WIN -> wins += 1
                    LOSSES -> losses += 1
                    DRAWS -> draws += 1
                }
            }

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as PlayerResult

                if (player != other.player) return false
                if (wins != other.wins) return false
                if (losses != other.losses) return false
                if (draws != other.draws) return false

                return true
            }

            override fun hashCode(): Int {
                var result = player.hashCode()
                result = 31 * result + wins
                result = 31 * result + losses
                result = 31 * result + draws
                return result
            }

            override fun toString(): String {
                return "PlayerResult(player=${player.name}, wins=$wins, losses=$losses, draws=$draws)"
            }
        }
    }
}
