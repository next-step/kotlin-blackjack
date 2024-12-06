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
            DealerScore: Int,
        ): BlackJackPlayResult {
            return when {
                DealerScore == BLACKJACK_NUMBER -> WIN
                normalPlayerScore > DealerScore -> WIN
                normalPlayerScore < DealerScore -> LOSE
                else -> DRAW
            }
        }
    }
}
