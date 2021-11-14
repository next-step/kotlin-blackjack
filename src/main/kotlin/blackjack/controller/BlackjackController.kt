package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Names
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

        while (true) {
            receiveCardAllPlayers = receiveCardAllPlayers(deck, players)
            if (receiveCardAllPlayers.isAllPlayerTurnOff()) {
                break
            }
        }
        return receiveCardAllPlayers
    }

    private fun receiveCardAllPlayers(deck: Deck, players: Players): Players {
        var receivedCardPlayers = players.copy()
        for (player in receivedCardPlayers) {
            val name = player.getPlayerName().name.toString()
            val receiveCardAnswer = InputView.askGamerReceiveMoreCard(name)
            if (receiveCardAnswer == PLAYER_TURN_ON_INPUT) {
                val receiveCardPlayer = player.receiveCard(deck.drawCard())
                receivedCardPlayers = receivedCardPlayers.updatePlayerStatus(player, receiveCardPlayer.turnOn())
                OutputView.printCards(receiveCardPlayer)
            }
            if (receiveCardAnswer == PLAYER_TURN_OFF_INPUT) {
                receivedCardPlayers = receivedCardPlayers.updatePlayerStatus(player, player.turnOff())
                OutputView.printCards(player)
            }
        }
        return receivedCardPlayers
    }

    companion object {
        private const val INIT_RECEIVE_CARD_COUNT = 2
        private const val PLAYER_TURN_OFF_INPUT = "n"
        private const val PLAYER_TURN_ON_INPUT = "y"
    }
}
