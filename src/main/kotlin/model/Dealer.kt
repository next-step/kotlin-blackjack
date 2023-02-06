package model

class Dealer(dealerName: String = DEALER) : Participant(dealerName) {
    fun isExtraCard(): Boolean {
        return sumOfCardNumber <= DEALER_INITIAL_SUM
    }

    companion object {
        private const val DEALER_INITIAL_SUM = 16
        private const val DEALER = "딜러"
    }
}
