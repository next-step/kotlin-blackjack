package blackjack.domain

class RuleChecker {

    fun checkSumOfCardNumbers(sum: Int): Boolean {
        return sum <= CONDITION_TO_WIN_BLACK_JACK
    }

    companion object {
        const val CONDITION_TO_WIN_BLACK_JACK = 21
    }
}
