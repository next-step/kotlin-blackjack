package blackjack

fun main() {
    val players = InputView.getPlayers()

    val blackJackGame = BlackJackGame.createGame(players, Deck())

    OutputView.printDefaultPlayerCards(blackJackGame.players)

    blackJackGame.players.forEach { player ->
        while (InputView.isDrawingCard(player) && blackJackGame.drawSingleCardToPlayer(player)
        ) {
            OutputView.printPlayerCards(player)
        }
    }

    OutputView.printResult(blackJackGame.players)
}
