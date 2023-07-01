package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.MAX_SCORE
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGameController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {
    fun run() {
        val players = Players(inputView.requestNameOfPlayers().map { Player(name = it) })
        val game = BlackJackGame(players = players)
        game.handOutDefaultCardToPlayers()

        outputView.printDefaultReceivedCards(players.values)

        game.players.values.forEach { player ->
            while (player.calculateScore() < MAX_SCORE) {
                if (inputView.requestReceiveAdditionalCard(player.name)) {
                    outputView.printlnPlayerCards(game.handOutAdditionalCard(player))
                    continue
                }
                break
            }
        }

        outputView.printGameResult(game.getGameResult())
    }
}
