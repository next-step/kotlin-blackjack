package blackjack.domain

data class Bet(val name: String, val money: Int) {
    val blackjack: Int = (money * 1.5).toInt()

    constructor(player: CardPlayer, money: Int) : this(player.name, money)
}
