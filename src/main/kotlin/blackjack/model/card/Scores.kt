package blackjack.model.card

import blackjack.model.Config

class Scores private constructor(private val scoreList: List<Int>) : List<Int> by scoreList {

    val state: State = bustOrNull(scoreList[0]) ?: blackJackOrRunning(scoreList)

    companion object {
        fun of(cardList: List<Card>): Scores {
            val basicScore = cardList.sumOf { it.denomination.score }
            val countOfAce = cardList.count { it.denomination == Denomination.ACE }
            return Scores(List(countOfAce + 1) { it * (Config.THE_OTHER_SCORE_OF_ACE_CARD - 1) + basicScore })
        }

        private fun bustOrNull(minScore: Int): State? = when {
            minScore > Config.BLACK_JACK_SCORE -> State.Bust(minScore)
            else -> null
        }

        private fun blackJackOrRunning(sortedScoreList: List<Int>): State = when (
            val maxScoreNotBust = sortedScoreList.filter { it <= Config.BLACK_JACK_SCORE }.maxOrNull() ?: 0
        ) {
            Config.BLACK_JACK_SCORE -> State.BlackJack()
            else -> State.Running(maxScoreNotBust)
        }
    }
}
