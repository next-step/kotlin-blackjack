package blackjack

class Bet(player: CardPlayer, betMoney: Int) {
    val name: String = player.name

    val win: Int = betMoney

    val blackjack: Int = (betMoney * 1.5).toInt()

    val lost: Int = -betMoney
}
