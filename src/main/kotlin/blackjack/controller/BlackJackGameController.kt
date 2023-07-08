package blackjack.controller

import blackjack.domain.BLACK_JACK
import blackjack.domain.BlackJackGame
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

const val DEALER_MINIMUM_SCORE = 16

class BlackJackGameController(
    private val inputView: InputView = InputView,
    private val outputView: OutputView = OutputView
) {
    fun run() {
        val players = Players(inputView.requestNameOfPlayers().map { Player(name = it) })
        val game = BlackJackGame(players = players)
        playGame(game)
        outputView.printScoreOfParticipants(game.players, game.dealer)
        outputView.printGameResult(game.players, game.dealer)
    }

    private fun playGame(game: BlackJackGame) {
        game.handOutDefaultCardToPlayers()
        outputView.printDefaultReceivedCards(game.players.values)

        game.players.values.forEach { player ->
            requestAndReceiveCard(player, game)
        }

        if (game.dealer.score <= DEALER_MINIMUM_SCORE) {
            game.handOutAdditionalCardTo(game.dealer)
            outputView.printlnDealerGetAdditionalCard()
        }
    }

    private fun requestAndReceiveCard(player: Player, game: BlackJackGame) {
        while (player.score < BLACK_JACK) {
            if (inputView.requestReceiveAdditionalCard(player.name)) {
                game.handOutAdditionalCardTo(player)
                outputView.printlnPlayerCards(player)
                continue
            }
            break
        }
    }
}
