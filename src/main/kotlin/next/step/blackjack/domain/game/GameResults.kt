package next.step.blackjack.domain.game

data class GameResults(val dealerGameResults: Map<GameResult, Int>, val playersGameResult: Map<String, GameResult>) {
    companion object {
        fun of(dealerGameResults: DealerGameResults, playersGameResult: PlayersGameResult): GameResults =
            GameResults(dealerGameResults.results(), playersGameResult.results())
    }
}