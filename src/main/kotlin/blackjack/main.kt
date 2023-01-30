package blackjack

import blackjack.domain.Draw
import blackjack.domain.Game
import blackjack.domain.Gamer
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val game = Game(InputView.getPersons().map { Gamer(it) })
    OutputView.printParticipantInfo(game)
    InputView.getMoney(game.gamerList)
    game.gamerList.forEach {
        do {
            val drawYn = Draw.checkDrawable(InputView.getDrawYn(it))
            it.addCard(drawYn)
            OutputView.printOwnCards(it)
        } while (it.isDrawable && drawYn)
    }
    game.dealer.changeDrawable()
    game.dealer.addCard(OutputView.printDealerCardAddYn(game))
    OutputView.printCardInfo(game)
    game.changeResult()
    game.settle()
    OutputView.printResult(game)
}
