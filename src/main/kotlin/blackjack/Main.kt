package blackjack

import blackjack.infrastructure.RandomDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val players = InputView.getPlayers()

    val blackJackGame = BlackJackGame.createGame(players, RandomDeck())

    OutputView.printDefaultPlayerCards(blackJackGame.players)

    if (blackJackGame.hasBlackJackPlayer()) {
        OutputView.printResult(blackJackGame.players)
    } else {
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
    }
    OutputView.printResult(blackJackGame.players)
}
