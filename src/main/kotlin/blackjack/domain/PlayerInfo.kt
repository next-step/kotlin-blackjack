package blackjack.domain

data class PlayerInfo(
    val name: Name,
    val bettingAmount: BettingAmount
) {
    constructor(name: Name) : this(name = name, BettingAmount(0))

    fun getName(): String = name.value
    fun getBettingAmount(): Int = bettingAmount.value
}
