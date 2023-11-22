package blackjack.model.game

import blackjack.model.player.Dealer
import blackjack.model.player.Player

data class MatchResult(val dealerResult: List<Rank>, val playerResults: Map<Player, Rank>) {
    companion object {
        fun toResult(dealer: Dealer, players: List<Player>): MatchResult {
            val playerResultsMap = players.associateWith { player ->
                when {
                    isWinningCondition(dealer, player) -> Rank.WIN
                    isDrawCondition(dealer, player) -> Rank.DRAW
                    else -> Rank.LOSE
                }
            }

            val dealerResults = playerResultsMap.map { !it.value }

            return MatchResult(dealerResults, playerResultsMap)
        }

        private fun isWinningCondition(dealer: Dealer, player: Player): Boolean {
            val dealerScore = dealer.score()
            val playerScore = player.score()

            return (dealer.isBust() && !player.isBust()) ||
                (!dealer.isBust() && !player.isBust() && (dealerScore < playerScore))
        }

        private fun isDrawCondition(dealer: Dealer, player: Player): Boolean {
            val dealerScore = dealer.score()
            val playerScore = player.score()

            return !dealer.isBust() && !player.isBust() && (dealerScore == playerScore)
        }
    }
}

enum class Rank(val rank: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    operator fun not(): Rank {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }
}
