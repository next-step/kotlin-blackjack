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
        val initPhasePlayers = initPhase(deck, players)
    }

    private fun initPhase(deck: Deck, players: Players): Players {
        var receivedCardPlayers = players.copy()
        (0 until INIT_RECEIVE_CARD_COUNT).forEach { _ ->
            receivedCardPlayers = receivedCardPlayers.receiveCardFromDeck(deck)
        }
        OutputView.printInitPhase(receivedCardPlayers)
        return receivedCardPlayers
    }

    companion object {
        private const val INIT_RECEIVE_CARD_COUNT = 2
    }
}
