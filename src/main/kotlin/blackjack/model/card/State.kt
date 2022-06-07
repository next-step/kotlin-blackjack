package blackjack.model.card

sealed class State(val scoreList: List<Int>, val finalScore: Int) {

    class Bust(scoreList: List<Int>, finalScore: Int) : State(scoreList, finalScore)
    class BlackJack(scoreList: List<Int>) : State(scoreList, BLACK_JACK_SCORE)
    class Running(scoreList: List<Int>, finalScore: Int) : State(scoreList, finalScore)

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val THE_OTHER_SCORE_OF_ACE_CARD = 10

        fun of(cardList: List<Card>): State {
            val basicScore = cardList.sumOf { it.denomination.score }
            val countOfAce = cardList.count { it.denomination == Denomination.ACE }
            val scoreList = (0..countOfAce).map { it * THE_OTHER_SCORE_OF_ACE_CARD + basicScore }.sorted()

            val minScore = scoreList.minOrNull() ?: 0
            val maxScoreNotBust = scoreList.filter { it <= BLACK_JACK_SCORE }.maxOrNull() ?: 0
            val isBlackJack = maxScoreNotBust == BLACK_JACK_SCORE
            val isBust = minScore > BLACK_JACK_SCORE

            return when {
                isBlackJack -> BlackJack(scoreList)
                isBust -> Bust(scoreList, minScore)
                else -> Running(scoreList, maxScoreNotBust)
            }
        }
    }
}
