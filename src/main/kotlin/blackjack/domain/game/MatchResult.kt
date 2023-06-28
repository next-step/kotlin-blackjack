package blackjack.domain.game

import blackjack.domain.card.Cards
import blackjack.domain.gamer.PlayerCards

data class MatchResult(
    val dealerCards: Cards,
    val allPlayerCards: List<PlayerCards>,
    val dealerMatchResult: DealerMatchResult,
    val playerMatchResults: List<PlayerMatchResult>,
) {

    init {
        require(dealerMatchResult.loseCount == playerMatchResults.count { it.matchResultType.isWin() }) {
            "dealer lose count should be player total win count"
        }
        require(dealerMatchResult.winCount == playerMatchResults.count { it.matchResultType.isLose() }) {
            "dealer win count should be player total lose count"
        }
        require(dealerMatchResult.tieCount == playerMatchResults.count { it.matchResultType.isTie() }) {
            "dealer tie count should be player total tie count"
        }
        require(allPlayerCards.size == playerMatchResults.size) {
            "all player cards size should be match result size"
        }
    }
}
