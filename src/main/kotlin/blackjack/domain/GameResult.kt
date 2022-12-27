package blackjack.domain

import blackjack.domain.Game.Companion.getScore

enum class GameResult(
    val label: String,
    val profit: (player: Player) -> Profit
) {
    WIN("승", { Profit.of(it.getBettingAmount(), it.isBlackjack()) }),
    LOSE("패", { Profit.ofLose(it.getBettingAmount()) }),
    DRAW("무", { Profit.of(0) });

    companion object {
        fun getGamePlayerResult(dealer: Dealer, player: Player): GameResult {
            return when (getGameResultOfDealer(dealer, player)) {
                WIN -> LOSE
                LOSE -> WIN
                else -> DRAW
            }
        }

        fun getGameResultOfDealer(dealer: Dealer, player: Player): GameResult {
            val score = getScore(dealer) - getScore(player)
            return of(score)
        }

        private fun of(score: Int): GameResult {
            return when {
                score > 0 -> WIN
                score == 0 -> DRAW
                else -> LOSE
            }
        }

    }
}
