package blackjack

fun main() {
    val players = InputView.getPlayers()

    val blackJackGame = BlackJackGame.createGame(players, Deck())

    OutputView.printDefaultPlayerCards(blackJackGame.players)

    blackJackGame.players.forEach { player ->
        while (
            player.couldAddCard() &&
            blackJackGame.drawSingleCardToPlayer(
                InputView.isDrawingCard(player),
                player,
            )
        ) {
            OutputView.printPlayerCards(player)
        }
    }

    OutputView.printResult(blackJackGame.players)
}
