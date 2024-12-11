package blackjack.domain

class Player(
    val name: String,
    private var bettingAmount: Int,
) : Participant() {
    fun getBettingAmount(): Int {
        return bettingAmount
    }
}
