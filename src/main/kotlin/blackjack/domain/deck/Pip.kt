package blackjack.domain.deck

import blackjack.BLACKJACK_COUNT

enum class Pip(val displayName: String, val score: Int) {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    companion object {
        private const val ACE_MIN = 1
        private const val ACE_MAX = 11

        fun scoreOf(pip: Pip, totalScore: Int = 0) = when (pip) {
            ACE -> getAceScore(totalScore)
            else -> pip.score
        }

        private fun getAceScore(totalScore: Int) = if (totalScore <= BLACKJACK_COUNT - ACE_MAX) ACE_MAX else ACE_MIN
    }
}
