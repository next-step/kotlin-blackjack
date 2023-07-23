package blackjack.controller

import blackjack.domain.BLACK_JACK
import blackjack.domain.BlackJackGame
import blackjack.domain.DEALER_MINIMUM_SCORE
import blackjack.domain.GameProfit
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGameController(
    private val inputView: InputView = InputView,
    private val outputView: OutputView = OutputView
) {
    fun run() {
        val playerNames = inputView.requestNameOfPlayers()
        val betAmounts = inputView.requestBetAmountOfPlayers(playerNames)
        val players = createPlayers(playerNames, betAmounts.map { it.toDouble() })

        val game = BlackJackGame(players = players)
        playGame(game)

        outputView.printScoreOfParticipants(game.players, game.dealer)
        outputView.printGameResult(game.players, game.dealer)
        outputView.printGameProfit(GameProfit(game.players, game.dealer).profitOfParticipants())
    }

    private fun createPlayers(playerNames: List<String>, betAmounts: List<Double>): Players {
        return Players(
            playerNames.mapIndexed { i, name ->
                Player(name = name, betAmount = betAmounts[i])
            }
        )
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
