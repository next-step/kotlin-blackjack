package blackjack.model.card

sealed class Score(val scoreList: List<Int>, val finalScore: Int) {

    val isBustOrBlackJack: Boolean
        get() = this is Bust || this is BlackJack

    class Bust(scoreList: List<Int>, finalScore: Int) : Score(scoreList, finalScore)
    class BlackJack(scoreList: List<Int>) : Score(scoreList, BLACK_JACK_SCORE)
    class Running(scoreList: List<Int>, finalScore: Int) : Score(scoreList, finalScore)

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val THE_OTHER_SCORE_OF_ACE_CARD = 10

        fun of(cardList: List<Card>): Score {
            val basicScore = cardList.sumOf { it.denomination.score }
            val countOfAce = cardList.count { it.denomination == Denomination.ACE }
            val scoreList = (0..countOfAce).map { it * THE_OTHER_SCORE_OF_ACE_CARD + basicScore }.sorted()

            val minScore = scoreList.minOrNull() ?: 0
            val maxScoreNotBust = scoreList.filter { it <= BLACK_JACK_SCORE }.maxOrNull() ?: 0
            val isBlackJack = scoreList.any { it == BLACK_JACK_SCORE }
            val isBust = minScore > BLACK_JACK_SCORE

            return when {
                isBlackJack -> BlackJack(scoreList)
                isBust -> Bust(scoreList, minScore)
                else -> Running(scoreList, maxScoreNotBust)
            }
        }
    }
}
