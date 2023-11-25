package blackjack

data class GamePlayer(
    val name: String,
    val betAmount: Int
) {
    constructor(name: String, betAmount: String): this(name, betAmount.toInt())
}
