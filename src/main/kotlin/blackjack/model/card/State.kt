package blackjack.model.card

sealed class State(val finalScore: Int) {

    class Bust(finalScore: Int) : State(finalScore)
    class BlackJack() : State(BLACK_JACK_SCORE)
    class Running(finalScore: Int) : State(finalScore)

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val THE_OTHER_SCORE_OF_ACE_CARD = 10

        fun List<Card>.toScoreList(): List<Int> {
            val basicScore = this.sumOf { it.denomination.score }
            val countOfAce = this.count { it.denomination == Denomination.ACE }
            return (0..countOfAce).map { it * THE_OTHER_SCORE_OF_ACE_CARD + basicScore }.sorted()
        }

        fun of(cardList: List<Card>): State {
            val scoreList = cardList.toScoreList()
            val minScore = scoreList.minOrNull() ?: 0
            val maxScoreNotBust = scoreList.filter { it <= BLACK_JACK_SCORE }.maxOrNull() ?: 0
            val isBlackJack = maxScoreNotBust == BLACK_JACK_SCORE
            val isBust = minScore > BLACK_JACK_SCORE

            return when {
                isBlackJack -> BlackJack()
                isBust -> Bust(minScore)
                else -> Running(maxScoreNotBust)
            }
        }
    }
}
