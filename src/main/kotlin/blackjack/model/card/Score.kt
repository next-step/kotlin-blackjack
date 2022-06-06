package blackjack.model.card

class Score(cardList: List<Card>) {

    val scoreList: List<Int>

    init {
        val basicScore = cardList.sumOf { it.denomination.score }
        val countOfAce = cardList.count { it.denomination == Denomination.ACE }
        scoreList = (0..countOfAce).map { it * THE_OTHER_SCORE_OF_ACE_CARD + basicScore }.sorted()
    }

    val finalScore: Int
        get() = when {
            this.isBlackJack -> BLACK_JACK_SCORE
            this.isBust -> this.minScore
            else -> this.maxScoreNotBust
        }

    val minScore: Int
        get() = this.scoreList.minOrNull() ?: 0

    val maxCore: Int
        get() = this.scoreList.maxOrNull() ?: 0

    val maxScoreNotBust: Int
        get() = this.scoreList.filter { it <= BLACK_JACK_SCORE }.maxOrNull() ?: 0

    val isBlackJack: Boolean
        get() = this.scoreList.any { it == BLACK_JACK_SCORE }

    val isBust: Boolean
        get() = this.minScore > BLACK_JACK_SCORE

    val isBustOrBlackJack: Boolean
        get() = this.isBust || this.isBlackJack

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val THE_OTHER_SCORE_OF_ACE_CARD = 10
    }
}
