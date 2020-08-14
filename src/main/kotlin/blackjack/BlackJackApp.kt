package blackjack

fun main() {

    val playerNames = InputView.requestPlayerNames()
    val (dealer, players) = Dealer() to playerNames.map(::Player)
    val game = BlackJackGame(dealer, players).apply {
        initCardForDealer()
        initCardForPlayers()
    }
    val gambleMoneyPerPlayer = InputView.requestGambleMoney(playerNames)

    ResultView.printPreview(game)
}
