package blackjack

import blackjack.domain.Game
import blackjack.domain.GameDealer

fun main() {
    val players = InputView.inputPlayers()
    val dealer = GameDealer()
    val game = Game(players, dealer)
    ResultView.printInitialCards(game.players, game.dealer)
    game.playPlayers(hit = InputView::shouldHit, printResult = ResultView::printPlayerCards)
    game.playDealer(ResultView::printDealerHit)
    ResultView.printPlayerResult(game.players, game.dealer)
    ResultView.printGameResult(game.results())
}

