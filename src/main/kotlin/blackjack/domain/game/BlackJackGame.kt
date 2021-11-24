package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.player.Players
import blackjack.views.OutputView

class BlackJackGame(private val players: Players, private val deck: Deck) {

    fun play(
        isPlayerTurnOff: Turn,
        initCallback: (Players) -> Unit,
        playingCallback: (Players) -> Unit,
    ): Players {
        val afterInitPhased = players.startInitPhase(deck)
        initCallback(afterInitPhased)
        val playingPhasedPlayers = playingPhase(isPlayerTurnOff, deck, afterInitPhased)
        playingCallback(playingPhasedPlayers)
        return playingPhasedPlayers
    }

    private fun playingPhase(isPlayerTurnOff: Turn, deck: Deck, players: Players): Players {
        lateinit var readyPlayers: Players
        readyPlayers = players.turnToReady()

        while (!readyPlayers.isAllPlayerTurnOff()) {
            readyPlayers = receiveCardAllPlayers(isPlayerTurnOff, deck, players)
        }
        return readyPlayers
    }

    private fun receiveCardAllPlayers(isPlayerTurnOff: Turn, deck: Deck, players: Players): Players {
        return players.receiveCards(deck, isPlayerTurnOff, OutputView::printCards, OutputView::printDealerCardReceived)
    }
}
