package blackjack.domain

class Bet(player: CardPlayer, val money: Int) {
    val name: String = player.name

    val blackjack: Int = (money * 1.5).toInt()
}
