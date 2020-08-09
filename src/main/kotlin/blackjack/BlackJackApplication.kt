package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.view.askFitFromPlayer
import blackjack.view.getPlayerNames
import blackjack.view.notifyStartGame
import blackjack.view.printPlayerCards
import blackjack.view.printResult

fun main() {
    val playerNames = getPlayerNames()
    val game = BlackJackGame(playerNames, Deck())
    notifyStartGame(playerNames)
    playerNames.forEach { printPlayerCards(it, game.playerCardsOf(it)) }
    playerNames.forEach {
        while (!game.isPlayerFinished(it)) {
            if (askFitFromPlayer(it)) {
                game.hit(it)
                printPlayerCards(it, game.playerCardsOf(it))
                continue
            }
            game.stand(it)
        }
    }
    playerNames.forEach { printResult(it, game.playerCardsOf(it), game.playerScoreOf(it)) }
}
