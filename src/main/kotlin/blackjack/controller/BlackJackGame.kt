package blackjack.controller

import blackjack.model.CardDistributor
import blackjack.model.DefaultCardDistributor
import blackjack.model.player.Player
import blackjack.model.player.PlayerProvider
import blackjack.view.output.OutputView

class BlackJackGame(
    playerProvider: PlayerProvider,
    private val cardDistributor: CardDistributor = DefaultCardDistributor(),
    private val outputView: OutputView? = null
) {

    private val players = playerProvider.createPlayers()

    fun run() {
        this.resetGame()
        this.giveTwoCardsToAll()

        outputView?.printInitialMessage(this.players)

        this.players.forEach { player ->
            players.blackJackPlayer?.let { return@forEach }
            hitOrStay(player)
        }
        outputView?.printCardsOfPlayer(players, withScore = true)
    }

    private fun hitOrStay(player: Player) {
        while (player.canHit) {
            hit(player)
            outputView?.printCardsOfPlayer(player)
        }
    }

    private fun hit(player: Player) {
        this.cardDistributor.giveCardsTo(player)
    }

    private fun giveTwoCardsToAll() {
        this.cardDistributor.giveCardsTo(this.players, 2)
    }

    private fun resetGame() {
        this.cardDistributor.resetCardSet()
        this.players.clearCard()
    }
}
