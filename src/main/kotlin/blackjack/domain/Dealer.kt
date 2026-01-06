package blackjack.domain

class Dealer : Participant(NAME) {
    fun isAdditionalDealCondition(): Boolean = score() <= ADD_CARD_BASE_SCORE

    companion object {
        const val NAME = "딜러"
        const val ADD_CARD_BASE_SCORE = 16
    }
}
