package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.game.BlackJackGame
import blackjack.domain.game.Rule
import blackjack.domain.game.Turn
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.views.InputView
import blackjack.views.OutputView

class BlackjackController() {

    private var isPlayerTurnOff: Turn = object : Turn {
        override fun isPlayerTurnOff(player: Player): Boolean {
            return InputView.askGamerReceiveMoreCard(player) != PLAYER_TURN_OFF_INPUT
        }
    }

    fun start() {
        val blackJackGame = BlackJackGame(Players.of(InputView.askGamerNames()), Deck())
        val players = blackJackGame.play(isPlayerTurnOff, OutputView::printInitPhase, OutputView::printPlayingPhase)
        val result = players.checkResult(Rule())
        OutputView.printGameResult(result)
    }

    companion object {
        private const val PLAYER_TURN_OFF_INPUT = "n"
    }
}
