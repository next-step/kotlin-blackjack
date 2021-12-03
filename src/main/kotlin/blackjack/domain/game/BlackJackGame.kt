package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.player.Players
import blackjack.views.OutputView

class BlackJackGame(var players: Players, private val deck: Deck) {

    fun play(
        getPlayerBetting: Bet,
        isPlayerTurnOff: Turn,
        initCallback: (Players) -> Unit
    ) {
        val afterInitPhased = players.startInitPhase(deck, getPlayerBetting)
        initCallback(afterInitPhased)
        this.players = playingPhase(isPlayerTurnOff, deck, afterInitPhased)
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

    fun getResult(): GameResult {
        return players.getResult()
    }
}
