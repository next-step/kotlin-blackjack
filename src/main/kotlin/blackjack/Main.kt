package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playerNames = InputView.inputPlayer()
    val players = BlackJackGame.startGame(playerNames)
    OutputView.outputPlayers(players)

    players.forEach(::takeMorePlayerCard)
    players.forEach(::showResultScore)
}

private fun takeMorePlayerCard(player: Player) {
    while (InputView.inputMoreCard(player).toLowerCase() == "y") {
        if (!player.isMoreCard()) break
        player.playerCards.add(BlackJackGame.addMoreCard())
        OutputView.showPlayerCard(player)
    }
}

private fun showResultScore(player: Player) {
    OutputView.showResultScore(player, player.playerCards.calculateScore())
}
