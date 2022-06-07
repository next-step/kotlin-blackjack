package blackjack.controller

import blackjack.model.CardDistributor
import blackjack.model.DefaultCardDistributor
import blackjack.model.player.PlayerProvider
import blackjack.view.output.OutputView

class BlackJackGame(
    playerProvider: PlayerProvider,
    private val cardDistributor: CardDistributor = DefaultCardDistributor(),
    private val outputView: OutputView? = null
) {

    private val players = playerProvider.createPlayers()

    fun run() {
        this.players.startNewGame(cardDistributor)
        outputView?.printInitialMessage(this.players)
        this.players.playGame(cardDistributor) { player ->
            outputView?.printCardsOfPlayer(player, withScore = false)
        }
        outputView?.printCardsOfPlayer(players, withScore = true)
    }
}
