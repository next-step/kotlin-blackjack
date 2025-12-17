package blackjack.domain

class Dealer() : Participant(DEALER_NAME) {
    fun isAdditionalDealCondition(): Boolean {
        return totalScore() <= DEALER_ADD_CARD_BASE_SCORE
    }
}
