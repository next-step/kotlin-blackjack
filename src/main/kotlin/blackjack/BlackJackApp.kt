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

    players.map {
        while (!Point.isReachMaxPoint(it.calculatePoint()) && InputView.requestOneOfCard(it) == "y") {
            it.requestCard(Deck.pop())
            ResultView.printCard(it)
        }
    }

    dealer.requestCardIfPossibleExtraCard(Deck.pop())
    ResultView.printResult(game)

    ResultView.printRevenue(game, gambleMoneyPerPlayer)
}
