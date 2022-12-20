package blackjack.domain

data class PlayerInfo(
    val name: Name,
    val bettingAmount: BettingAmount = BettingAmount(0)
) {
    constructor(name: String) : this(name = Name(name))

    fun getName(): String = name.value
    fun getBettingAmount(): Int = bettingAmount.value
}
