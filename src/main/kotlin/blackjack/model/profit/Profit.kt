package blackjack.model.profit

data class Profit(val participantName: String, val profit: Int) {

    constructor(participantName: String, profit: Double) : this(
        participantName,
        profit.toInt()
    )
}
