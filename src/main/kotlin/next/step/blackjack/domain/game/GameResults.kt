package next.step.blackjack.domain.game

data class GameResults(val playersResult: Map<String, GameResult>, val dealerResult: Map<GameResult, Int>) {

    companion object {
        fun from(playersResult: Map<String, GameResult>): GameResults =
            GameResults(playersResult, dealerResult(playersResult))

        private fun dealerResult(playersResult: Map<String, GameResult>) =
            playersResult.map { it.value.opposite() }
                .groupBy { it }
                .map { it.key to it.value.size }
                .toMap()

    }
}
