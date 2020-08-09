package blackjack

fun main() {
    val players = registerPlayers()

    drawDeck(players)
    showResult(players)
}

private fun showResult(players: List<Player>) {
    players.map {
        ResultView.printResult(it, Winner.calculateRank(it.myReceivedDeck))
    }
}

private fun drawDeck(players: List<Player>) {
    players.map {
        while (isContinueDraw(it)) {
            it.requestDeck(Deck.pop())
            ResultView.printPlayerHaveDeck(it)
        }
    }
}

private fun isContinueDraw(it: Player) =
    Winner.calculateRank(it.myReceivedDeck) < Winner.WINNING_RANK && InputView.requestOneOfDeck(it) == "y"

private fun registerPlayers(): List<Player> {
    val playerName = InputView.requestPlayerNames()
    val players = playerName.map(::Player).also { player ->
        player.map {
            it.requestDeck(Deck.pop())
            it.requestDeck(Deck.pop())
        }
    }
    ResultView.printPreGame(players)
    return players
}
