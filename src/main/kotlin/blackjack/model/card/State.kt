package blackjack.model.card

sealed class State(val finalScore: Int) {

    class Bust(finalScore: Int) : State(finalScore)
    class BlackJack() : State(BLACK_JACK_SCORE)
    class Running(finalScore: Int) : State(finalScore)

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val THE_OTHER_SCORE_OF_ACE_CARD = 10

        fun List<Card>.toSortedScoreList(): List<Int> {
            val basicScore = this.sumOf { it.denomination.score }
            val countOfAce = this.count { it.denomination == Denomination.ACE }
            return List(countOfAce + 1) { it * THE_OTHER_SCORE_OF_ACE_CARD + basicScore }
        }

        fun of(cardList: List<Card>): State {
            val scoreList = cardList.toSortedScoreList()
            return bustOrNull(scoreList[0]) ?: blackJackOrRunning(scoreList)
        }

        private fun bustOrNull(minScore: Int): State? = when {
            minScore > BLACK_JACK_SCORE -> Bust(minScore)
            else -> null
        }

        private fun blackJackOrRunning(sortedScoreList: List<Int>): State = when (
            val maxScoreNotBust = sortedScoreList.filter { it <= BLACK_JACK_SCORE }.maxOrNull() ?: 0
        ) {
            BLACK_JACK_SCORE -> BlackJack()
            else -> Running(maxScoreNotBust)
        }
    }
}
