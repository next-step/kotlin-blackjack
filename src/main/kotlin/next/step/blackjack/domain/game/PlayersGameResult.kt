package next.step.blackjack.domain.game

data class PlayersGameResult(private val results: MutableMap<String, GameResult>) {

    fun put(name: String, gameResult: GameResult) {
        results[name] = gameResult
    }

    fun results(): Map<String, GameResult> = results.toMap()

    companion object {
        fun of(results: MutableMap<String, GameResult>) = PlayersGameResult(results)

        fun empty(): PlayersGameResult = PlayersGameResult(mutableMapOf())
    }
}