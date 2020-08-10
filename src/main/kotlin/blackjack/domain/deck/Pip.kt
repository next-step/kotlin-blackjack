package blackjack.domain.deck

import blackjack.BLACKJACK_COUNT

enum class Pip(val displayName: String) {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    companion object {
        private const val ACE_MIN = 1
        private const val ACE_MAX = 11

        fun scoreOf(pip: Pip, totalScore: Int = 0) = when (pip) {
            ACE -> getAceScore(totalScore)
            JACK, QUEEN, KING -> 10
            else -> pip.displayName.toInt()
        }

        private fun getAceScore(totalScore: Int) = if (totalScore <= BLACKJACK_COUNT - ACE_MAX) ACE_MAX else ACE_MIN
    }
}
