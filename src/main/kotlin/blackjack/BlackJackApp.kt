package blackjack

fun main() {

    val playerNames = InputView.requestPlayerNames()
    val (dealer, players) = Dealer() to playerNames.map(::Player)
    val gambleMoneyPerPlayer = InputView.requestGambleMoney(playerNames)
}
