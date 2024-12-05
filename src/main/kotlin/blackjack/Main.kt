package blackjack

fun main() {
    val players = InputView.getPlayers()

    val blackJackGame = BlackJackGame.createGame(players, Deck())

    OutputView.printDefaultPlayerCards(blackJackGame.players)

    blackJackGame.players.forEach { player ->
        while (
            (player.couldDraw() && InputView.drawOrStay(player)) &&
            blackJackGame.drawSingleCardToPlayer(player)
        ) {
            OutputView.printPlayerCards(player)
            if (player.isBust()) {
                OutputView.printBustMessage(player)
                break
            }
        }
    }

    OutputView.printResult(blackJackGame.players)
}
