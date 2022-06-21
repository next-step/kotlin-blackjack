package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.service.InputParser
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackView
import blackjack.view.DealerView
import blackjack.view.PlayerView

class BlackjackController {

    fun start() {
        BlackjackView.printPlayerInput()
        val players = getPlayers()

        Blackjack(players).let {
            it.drawFirstCards()
            BlackjackView.printInitialize(it.players)
            BlackjackView.printCards(it)
            startGame(it)
            BlackjackView.printResult(it)
        }
    }

    private fun getPlayers(): List<Player> {
        val names = BlackjackInputView.readPlayerNames()

        return InputParser.parsePlayerName(names).map(::Player)
    }

    private fun startGame(blackjack: Blackjack) {
        blackjack.apply {
            players.forEach {
                while (drawable(it)) {
                    drawCard(it)
                    PlayerView.printCards(it)
                }
            }
            while (dealer.drawable()) {
                drawCard(dealer)
                DealerView.printMoreCard(dealer)
                PlayerView.printCards(dealer)
            }
        }
    }

    private fun drawable(player: Player) = when {
        !player.drawable() -> false
        player is Dealer -> true
        else -> {
            BlackjackView.printMoreCard(player.name)
            InputParser.parseMoreCard(BlackjackInputView.readMoreCard())
        }
    }
}
