package domain

class Dealer(name: String) : Player(name) {

    fun isBelowForDealerScore(score: Int): Boolean {
        return MINIMUM_SCORE_FOR_DEALER >= score
    }

    companion object {
        const val MINIMUM_SCORE_FOR_DEALER = 16
    }
}
