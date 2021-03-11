package blackjack

enum class Result(val result: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    companion object {
        fun getDealerResult(playerResults: List<Pair<String, Result>>): List<Result> {
            return playerResults.map {
                when {
                    it.second.result === WIN.result -> Result.LOSE
                    it.second.result === LOSE.result -> Result.WIN
                    else -> Result.DRAW
                }
            }
        }
    }
}
