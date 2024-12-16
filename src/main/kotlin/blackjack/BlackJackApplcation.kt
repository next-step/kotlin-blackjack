package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.GameCards
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val players = InputView.enterParticipatingPlayers()
    val gameCards = GameCards.create()
    val blackJackGame = BlackJackGame(players, gameCards)
    blackJackGame.startGame()
    OutputView.printFirstAllPlayersCards(players)
    players.forEach { player ->
        blackJackGame.handlePlayerDraw(player, gameCards)
    }
    OutputView.printFinalResults(players)
}
