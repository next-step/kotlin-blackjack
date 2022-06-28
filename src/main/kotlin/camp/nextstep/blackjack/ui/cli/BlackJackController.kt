package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.game.Action
import camp.nextstep.blackjack.game.BlackJackGame

object BlackJackController {

    fun newGame(): BlackJackGame {
        val gamblers = BlackJackReader.readGamblers()
        val newGame = BlackJackGame.new(gamblers)

        BlackJackWriter.write(newGame.participants)
        BlackJackWriter.write(newGame.dealer)

        for (gambler in gamblers) {
            BlackJackWriter.write(gambler)
        }

        return newGame
    }

    fun playGame(game: BlackJackGame) {
        val turns = game.gamblerTurns
        for (turn in turns) {
            BlackJackWriter.write(turn.player)
            game.play(turn, { BlackJackReader.readGamblerAction(turn.player) }) { BlackJackWriter.write(turn.player) }
        }

        val dealerTurn = game.dealerTurn
        game.dealersPlay(dealerTurn) {
            if (it == Action.HIT) println("딜러의 카드 합이 16 이하로, 한 장의 카드를 더 받았습니다.")
            else println("딜러의 카드 합이 16 보다 커서 더 이상 카드를 뽑지 않습니다.")
            BlackJackWriter.write(game.dealer)
        }
    }

    fun closeGame(game: BlackJackGame) {
        val result = game.result()
        BlackJackWriter.write(result)
    }
}
