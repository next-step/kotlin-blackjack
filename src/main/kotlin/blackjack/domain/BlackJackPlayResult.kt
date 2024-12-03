package blackjack.domain

enum class BlackJackPlayResult(val resultString: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    companion object {
        fun getResult(
            myScore: Int,
            opponentScore: Int,
        ): BlackJackPlayResult {
            return when {
                myScore > opponentScore -> WIN
                myScore < opponentScore -> LOSE
                else -> DRAW
            }
        }
    }
}
