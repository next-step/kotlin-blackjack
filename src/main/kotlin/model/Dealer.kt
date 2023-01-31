package model

class Dealer(dealerName: String) : Participant(dealerName) {
    fun isExtraCard(): Boolean {
        return sumOfCardNumber <= DEALER_INITIAL_SUM
    }

    companion object {
        private const val DEALER_INITIAL_SUM = 16
    }
}
