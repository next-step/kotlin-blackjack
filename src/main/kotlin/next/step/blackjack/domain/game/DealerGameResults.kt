package next.step.blackjack.domain.game

data class DealerGameResults(private val results: MutableMap<GameResult, Int>) {
    fun add(gameResult: GameResult) {
        results[gameResult] = results[gameResult]!! + 1
    }

    fun results(): Map<GameResult, Int> = results.filterNot { it.key == GameResult.UNDECIDED }.toMap()

    companion object {

        fun of(results: MutableMap<GameResult, Int>) = DealerGameResults(results)
        
        fun zeros(): DealerGameResults = DealerGameResults(
            GameResult.values().associateWith { 0 }.toMutableMap()
        )
    }
}