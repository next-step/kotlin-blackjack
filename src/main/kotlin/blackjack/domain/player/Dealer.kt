package blackjack.domain.player

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.MatchResult
import blackjack.domain.Player
import blackjack.domain.PlayerMatchResult
import kotlin.math.absoluteValue

internal class Dealer : Player("딜러") {
    override val visibleCards: List<Card>
        get() {
            if (this.cards.isEmpty()) {
                return listOf()
            }
            return this.cards.subList(0, 1)
        }

    override fun canHit(): Boolean = score() <= HITTABLE_MAX_SCORE

    fun match(players: List<Player>): PlayerMatchResult {
        val playerResult: Map<Player, MatchResult> = players.map { it to match(it) }.toMap()
        return PlayerMatchResult(playerResult.values.map { it.findOpposite() }, playerResult)
    }

    private fun match(player: Player): MatchResult {
        if (score() > BlackJackGame.MAX_SCORE) {
            return MatchResult.WIN
        }

        val dealerGap = BlackJackGame.MAX_SCORE - this.score()
        val playerGap = (BlackJackGame.MAX_SCORE - player.score()).absoluteValue

        if (dealerGap < playerGap) {
            return MatchResult.LOSE
        }
        if (dealerGap == playerGap) {
            return MatchResult.DRAW
        }

        return MatchResult.WIN
    }

    companion object {
        private const val HITTABLE_MAX_SCORE = 16
    }
}
