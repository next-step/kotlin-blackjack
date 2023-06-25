package next.step.blackjack.domain.game

data class GameResults(val dealerGameResults: DealerGameResults, val playersGameResult: PlayersGameResult) {
    companion object {
        fun of(dealerGameResults: DealerGameResults, playersGameResult: PlayersGameResult): GameResults =
            GameResults(dealerGameResults, playersGameResult)
    }
}