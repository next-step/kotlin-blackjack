package blackjack

fun main() {
    val playerName = InputView.requestPlayerNames()
    val players = playerName.map(::Player).also { player ->
        player.map {
            it.requestDeck(Deck.pop())
            it.requestDeck(Deck.pop())
        }
    }
    ResultView.printPreGame(players)

    players.map {
        while (Winner.calculateRank(it.myReceivedDeck) < Winner.WINNING_RANK && InputView.requestOneOfDeck(it) == "y") {
            it.requestDeck(Deck.pop())
            ResultView.printPlayerHaveDeck(it)
        }
    }

    players.map {
        ResultView.printResult(it, Winner.calculateRank(it.myReceivedDeck))
    }
}
