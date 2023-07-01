package blackjack.domain

class RuleChecker {

    fun checkSumOfCardNumbers(blackJackGamer: BlackJackGamer): Boolean {
        return blackJackGamer.calculateSumOfCardNumbers() <= proceedCondition(blackJackGamer.getGamerType())
    }

    private fun proceedCondition(gamerType: GamerType): Int {
        if (gamerType == GamerType.PLAYER) return CONDITION_TO_WIN_BLACK_JACK
        return CONDITION_TO_DEALER_DRAW_CARD
    }

    companion object {
        const val CONDITION_TO_WIN_BLACK_JACK = 21
        const val CONDITION_TO_DEALER_DRAW_CARD = 16
    }
}
