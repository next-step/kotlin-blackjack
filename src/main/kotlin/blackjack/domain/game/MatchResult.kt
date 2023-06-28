package blackjack.domain.game

data class MatchResult(
    val gamerCards: GamerCards,
    val gamerMatchResult: GamerMatchResult,
) {

    init {
        require(gamerCards.allPlayerCards.size == gamerMatchResult.playerMatchResults.size) {
            "all player cards size should be match result size"
        }
    }
}
