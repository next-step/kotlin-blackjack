package blackjack.controller

import blackjack.model.CardDistributor
import blackjack.model.DefaultCardDistributor
import blackjack.model.PlayRoom
import blackjack.model.player.Player
import blackjack.model.player.PlayerBet
import blackjack.model.player.PlayerBets
import blackjack.model.player.PlayerProvider
import blackjack.model.player.Players
import blackjack.view.output.OutputView

class BlackJackGame(
    private val playerProvider: PlayerProvider,
    private val cardDistributor: CardDistributor = DefaultCardDistributor(),
    private val outputView: OutputView? = null
) {

    private val dealer = playerProvider.createDealer()
    private val guests: Players<Player.Guest> by lazy {
        playerProvider.createGuestPlayers()
    }

    fun run() {

        val playerBets = PlayerBets(this.guests.map { PlayerBet(it, playerProvider.betForPlayer(it)) })

        val playRoom = PlayRoom(
            cardDistributor = cardDistributor,
            dealer = dealer,
            playerBets = playerBets
        )
        playRoom.startNewGame()
        outputView?.printInitialMessage(playRoom)
        val records = playRoom.playGame { player ->
            outputView?.onPlayerHit(player)
        }
        outputView?.printCardsOfPlayRoom(playRoom, isGameOver = true)
        outputView?.printPlayerRecords(records)
    }
}
