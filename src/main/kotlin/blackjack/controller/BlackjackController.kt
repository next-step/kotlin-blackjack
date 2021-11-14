package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Names
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.views.InputView
import blackjack.views.OutputView

class BlackjackController() {

    fun start() {
        val names = Names.generateNames(InputView.askGamerNames())
        val players = Players.createGamers(names)
        val deck = Deck()
        val initPhasedPlayers = initPhase(deck, players)
        val playingPhasedPlayers = playingPhase(deck, initPhasedPlayers)
        OutputView.printPlayingPhase(playingPhasedPlayers)
    }

    private fun initPhase(deck: Deck, players: Players): Players {
        var receivedCardPlayers = players.copy()
        (0 until INIT_RECEIVE_CARD_COUNT).forEach { _ ->
            receivedCardPlayers = receivedCardPlayers.receiveCardFromDeck(deck)
        }
        OutputView.printInitPhase(receivedCardPlayers)
        return receivedCardPlayers
    }

    private fun playingPhase(deck: Deck, players: Players): Players {
        lateinit var receiveCardAllPlayers: Players
        receiveCardAllPlayers = receiveCardAllPlayers(deck, players)

        while (!receiveCardAllPlayers.isAllPlayerTurnOff()) {
            receiveCardAllPlayers = receiveCardAllPlayers(deck, players)
        }
        return receiveCardAllPlayers
    }

    private fun receiveCardAllPlayers(deck: Deck, players: Players): Players {
        var receivedCardPlayers = players.copy()
        for (player in receivedCardPlayers) {
            receivedCardPlayers = addMoreCards(player, deck, receivedCardPlayers)
        }
        return receivedCardPlayers
    }

    private fun addMoreCards(
        player: Player,
        deck: Deck,
        players: Players
    ): Players {
        val name = player.getPlayerName().name.toString()
        var receivedCardPlayers = players
        var receiveCardPlayer = player
        while (isPlayerTurnOff(name)) {
            val update = receiveCardPlayer.receiveCard(deck.drawCard())
            receivedCardPlayers = receivedCardPlayers.updatePlayerStatus(receiveCardPlayer, update)

            val updatedStatus = update.turnOn()
            receivedCardPlayers = receivedCardPlayers.updatePlayerStatus(update, updatedStatus)
            receiveCardPlayer = updatedStatus
            OutputView.printCards(receiveCardPlayer)
        }
        receivedCardPlayers = receivedCardPlayers.updatePlayerStatus(receiveCardPlayer, receiveCardPlayer.turnOff())
        OutputView.printCards(receiveCardPlayer)
        return receivedCardPlayers
    }

    private fun isPlayerTurnOff(name: String) = InputView.askGamerReceiveMoreCard(name) != PLAYER_TURN_OFF_INPUT

    companion object {
        private const val INIT_RECEIVE_CARD_COUNT = 2
        private const val PLAYER_TURN_OFF_INPUT = "n"
    }
}
