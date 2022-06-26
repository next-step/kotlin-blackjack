package camp.nextstep.blackjack

import camp.nextstep.blackjack.game.Action
import camp.nextstep.blackjack.game.BlackJackGame
import camp.nextstep.blackjack.ui.cli.DealerCardsWriter
import camp.nextstep.blackjack.ui.cli.GamblerActionReader
import camp.nextstep.blackjack.ui.cli.GamblerCardsWriter
import camp.nextstep.blackjack.ui.cli.GamblersReader
import camp.nextstep.blackjack.ui.cli.GameInitializedInfoWriter
import camp.nextstep.blackjack.ui.cli.GameResultsWriter

fun main() {

    val gamblers = GamblersReader.read()
    val game = BlackJackGame.new(gamblers)

    GameInitializedInfoWriter.write(game.participants)
    DealerCardsWriter.write(game.dealer)

    for (gambler in gamblers) {
        GamblerCardsWriter.write(gambler)
    }

    val turns = game.gamblerTurns
    for (turn in turns) {
        GamblerCardsWriter.write(turn.player)
        game.play(turn, { GamblerActionReader.read(turn.player) }) { GamblerCardsWriter.write(turn.player) }
    }

    val dealerTurn = game.dealerTurn
    game.dealersPlay(dealerTurn) {
        if (it == Action.HIT) println("딜러의 카드 합이 16 이하로, 한 장의 카드를 더 받았습니다.")
        else println("딜러의 카드 합이 16 보다 커서 더 이상 카드를 뽑지 않습니다.")
        DealerCardsWriter.write(game.dealer)
    }

    val result = game.result()
    GameResultsWriter.write(result)
}
