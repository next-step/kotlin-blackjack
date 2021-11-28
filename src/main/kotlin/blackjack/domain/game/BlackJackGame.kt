package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.player.Players
import blackjack.views.OutputView

class BlackJackGame(var players: Players, private val deck: Deck) {
    private lateinit var bettings: Bettings

    fun play(
        getPlayerBetting: Bet,
        isPlayerTurnOff: Turn,
        initCallback: (Players) -> Unit
    ) {
        bettings = Bettings.from(players.getBettings(getPlayerBetting))
        val afterInitPhased = players.startInitPhase(deck)
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
        return players.getResult(bettings)
    }
}
