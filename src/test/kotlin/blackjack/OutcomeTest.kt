package blackjack

import blackjack.OutcomeTest.PlayerResult
import blackjack.Player.Person.PlayResult
import blackjack.Player.Person.PlayResult.DRAWS
import blackjack.Player.Person.PlayResult.LOSSES
import blackjack.Player.Person.PlayResult.WIN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    private val pobi = Player.Person("pobi")
        .apply {
            accept(Card("A", Symbol.DIAMONDS))
            accept(Card("K", Symbol.DIAMONDS))
        }
    private val jason = Player.Person("jason")
        .apply {
            accept(Card("K", Symbol.DIAMONDS))
            accept(Card("2", Symbol.DIAMONDS))
        }
    private val dealer = Player.Dealer()
        .apply {
            accept(Card("K", Symbol.DIAMONDS))
            accept(Card("K", Symbol.DIAMONDS))
        }

    @Test
    internal fun `승패를 계산한다`() {
        val result = result {
            update(listOf(pobi, jason) vs dealer)
        }.build()

        assertThat(result).contains(
            PlayerResult(dealer, wins = 1, losses = 1),
            PlayerResult(pobi, wins = 1),
            PlayerResult(jason, losses = 1)
        )
    }

    private fun result(builder: PlayResultBuilder.() -> Unit): PlayResultBuilder {
        return PlayResultBuilder().apply(builder)
    }

    class PlayResultBuilder {
        private val playerResult: MutableList<PlayerResult> = mutableListOf()

        fun update(list: List<PlayerResult>) {
            playerResult.addAll(list)
        }

        fun update(result: PlayerResult) {
            playerResult.add(result)
        }

        fun build(): List<PlayerResult> {
            return playerResult
        }
    }

    data class PlayerResult(
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
    }
}

infix fun List<Player>.vs(other: Player): List<PlayerResult> {
    return map { it to (it vs other) }
        .map { (player, playResult) ->
            PlayerResult(player)
                .apply {
                    update(playResult)
                }
        } + (other vs this)
}

infix fun Player.vs(players: List<Player>): PlayerResult {
    val thisPlayer = this
    return PlayerResult(this)
        .apply {
            for (player in players) {
                update(thisPlayer vs player)
            }
        }
}
