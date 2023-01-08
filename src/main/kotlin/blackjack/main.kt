package blackjack

import blackjack.domain.Draw
import blackjack.domain.Game
import blackjack.domain.Gamer
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val game = Game(InputView.getPersons().map { Gamer(it) })
    OutputView.printParticipantInfo(game)
    game.gamerList.forEach {
        do {
            val drawYn = Draw.checkDrawable(InputView.getDrawYn(it))
            it.addCard(drawYn)
            OutputView.printOwnCards(it)
        } while (it.checkDrawable() && drawYn)
    }
    game.dealer.addCard(OutputView.printDealerCardAddYn(game))
    OutputView.printCardInfo(game)
    game.changeResult()
    OutputView.printResult(game)
}
