package blackjack

fun main() {

    val players = InputView.requestPlayerNames().map(::Player)
    val gambleMoneyPerPlayer = InputView.requestGambleMoney(players)
}
