package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.game.Bet
import blackjack.domain.game.BlackJackGame
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

    private var getPlayerBetting: Bet = object : Bet {
        override fun getPlayerBetting(player: Player): Int {
            return InputView.askGamerBetting(player)
        }
    }

    fun start() {
        val blackJackGame = BlackJackGame(Players.from(InputView.askGamerNames()), Deck())
        blackJackGame.play(getPlayerBetting, isPlayerTurnOff, OutputView::printInitPhase)
        OutputView.printPlayingPhase(blackJackGame.players)
        val result = blackJackGame.getResult()
        OutputView.printGameResult(result.scoreResult)
        OutputView.printProfit(result.players)
    }

    companion object {
        private const val PLAYER_TURN_OFF_INPUT = "n"
    }
}
