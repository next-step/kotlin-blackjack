package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Names
import blackjack.domain.player.Player
import blackjack.domain.player.Players
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
        lateinit var readyPlayers: Players
        readyPlayers = players.turnToReady()

        while (!readyPlayers.isAllPlayerTurnOff()) {
            readyPlayers = receiveCardAllPlayers(deck, players)
        }
        return readyPlayers
    }

    private fun receiveCardAllPlayers(deck: Deck, players: Players): Players {
        var receivedCardPlayers = players
        for (player in receivedCardPlayers.players) {
            receivedCardPlayers = receiveCard(player, deck, receivedCardPlayers)
        }
        return receivedCardPlayers
    }

    private fun receiveCard(
        player: Player,
        deck: Deck,
        players: Players
    ): Players {
        val name = player.getPlayerName().name.toString()
        var updatedPlayers = players
        var myTurnPlayer = player
        while (isPlayerTurnOff(name)) {
            val result = updatedPlayers.receiveCards(myTurnPlayer, deck)
            updatedPlayers = result.players
            myTurnPlayer = result.player
            OutputView.printCards(myTurnPlayer)
        }
        updatedPlayers = updatedPlayers.endPlayerTurn(myTurnPlayer)
        OutputView.printCards(myTurnPlayer)
        return updatedPlayers
    }

    private fun isPlayerTurnOff(name: String) = InputView.askGamerReceiveMoreCard(name) != PLAYER_TURN_OFF_INPUT

    companion object {
        private const val INIT_RECEIVE_CARD_COUNT = 2
        private const val PLAYER_TURN_OFF_INPUT = "n"
    }
}
