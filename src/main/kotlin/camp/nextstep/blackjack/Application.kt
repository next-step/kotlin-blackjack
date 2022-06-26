package camp.nextstep.blackjack

import camp.nextstep.blackjack.game.BlackJackGame
import camp.nextstep.blackjack.ui.cli.DealerCardsWriter
import camp.nextstep.blackjack.ui.cli.GamblerActionReader
import camp.nextstep.blackjack.ui.cli.GamblerCardsWriter
import camp.nextstep.blackjack.ui.cli.GamblersReader
import camp.nextstep.blackjack.ui.cli.GameInitializedInfoWriter
import camp.nextstep.blackjack.ui.cli.GameResultWriter

fun main() {

    val gamblers = GamblersReader.read()
    val game = BlackJackGame.new(gamblers)

    GameInitializedInfoWriter.write(game.participants)
    DealerCardsWriter.write(game.dealer)

    for (gambler in gamblers) {
        GamblerCardsWriter.write(gambler)
    }

    val turns = game.turns
    for (turn in turns) {
        GamblerCardsWriter.write(turn.gambler)
        game.play(turn, { GamblerActionReader.read(turn.gambler) }) { GamblerCardsWriter.write(turn.gambler) }
    }

    val result = game.result()
    GameResultWriter.write(result)
}
