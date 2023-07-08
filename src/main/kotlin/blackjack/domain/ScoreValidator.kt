package blackjack.domain

object ScoreValidator {
    fun isValidBlackjackScoreWithAceAsEleven(score: Int): Boolean {
        return score + Rank.ACE_MAX - Rank.ACE_MIN <= BLACK_JACK
    }
}
