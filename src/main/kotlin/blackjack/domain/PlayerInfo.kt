package blackjack.domain

data class PlayerInfo(
    private val name: Name,
    private val bettingAmount: BettingAmount = BettingAmount(0)
) {
    constructor(name: String) : this(name = Name(name))

    fun getName(): String = name.value
    fun getBettingAmount(): BettingAmount = bettingAmount
}
