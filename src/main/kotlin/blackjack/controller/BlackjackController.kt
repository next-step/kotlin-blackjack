package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.game.Turn
import blackjack.domain.player.Names
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.views.InputView
import blackjack.views.OutputView

class BlackjackController() {

    var isPlayerTurnOff: Turn = object : Turn {
        override fun isPlayerTurnOff(player: Player): Boolean {
            return InputView.askGamerReceiveMoreCard(player) != PLAYER_TURN_OFF_INPUT
        }
    }

    fun start() {
        val players = createGamer()
        val deck = Deck()
        val afterInitPhased = players.startInitPhase(deck)
        OutputView.printInitPhase(afterInitPhased)
        val playingPhasedPlayers = playingPhase(deck, afterInitPhased)
        OutputView.printPlayingPhase(playingPhasedPlayers)
    }

    private fun createGamer(): Players {
        val names = Names.generateNames(InputView.askGamerNames())
        return Players.createGamers(names)
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
        return players.receiveCards(deck, isPlayerTurnOff)
    }

    companion object {
        private const val PLAYER_TURN_OFF_INPUT = "n"
    }
}
