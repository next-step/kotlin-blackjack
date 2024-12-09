package blackjack.domain

enum class BlackJackPlayResult(val resultString: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    companion object {
        private const val BLACKJACK_NUMBER = 21

        fun getResult(
            normalPlayerScore: Int,
            dealerScore: Int,
        ): BlackJackPlayResult {
            return when {
                dealerScore == BLACKJACK_NUMBER -> WIN
                normalPlayerScore > dealerScore -> WIN
                normalPlayerScore < dealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
