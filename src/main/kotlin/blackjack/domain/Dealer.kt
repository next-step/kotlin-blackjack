package blackjack.domain

class Dealer(
    val name: String = "딜러",
) : Participant() {
    companion object {
        const val DEALER_UNDER_NUMBER: Int = 16
    }
}
