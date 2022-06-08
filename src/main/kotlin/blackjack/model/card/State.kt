package blackjack.model.card

import blackjack.model.Config

sealed interface State {
    val finalScore: Int

    data class Bust(override val finalScore: Int) : State
    data class BlackJack(override val finalScore: Int = Config.BLACK_JACK_SCORE) : State
    data class Running(override val finalScore: Int) : State

    companion object {

        fun List<Card>.toSortedScoreList(): List<Int> {
            val basicScore = this.sumOf { it.denomination.score }
            val countOfAce = this.count { it.denomination == Denomination.ACE }
            return List(countOfAce + 1) { it * (Config.THE_OTHER_SCORE_OF_ACE_CARD - 1) + basicScore }
        }

        fun of(cardList: List<Card>): State {
            val scoreList = cardList.toSortedScoreList()
            return bustOrNull(scoreList[0]) ?: blackJackOrRunning(scoreList)
        }

        private fun bustOrNull(minScore: Int): State? = when {
            minScore > Config.BLACK_JACK_SCORE -> Bust(minScore)
            else -> null
        }

        private fun blackJackOrRunning(sortedScoreList: List<Int>): State = when (
            val maxScoreNotBust = sortedScoreList.filter { it <= Config.BLACK_JACK_SCORE }.maxOrNull() ?: 0
        ) {
            Config.BLACK_JACK_SCORE -> BlackJack()
            else -> Running(maxScoreNotBust)
        }
    }
}
