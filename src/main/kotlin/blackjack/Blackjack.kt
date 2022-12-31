package blackjack

import blackjack.domain.Game
import blackjack.domain.GameDealer

fun main() {
    val game = createGame()
    ResultView.printInitialCards(game.players, game.dealer)
    playBlackjack(game)
    printGameResults(game)
}

private fun createGame(): Game {
    val players = InputView.inputPlayers()
    val dealer = GameDealer()
    return Game(players, dealer)
}

private fun playBlackjack(game: Game) {
    game.playPlayers(hit = InputView::shouldHit, printResult = ResultView::printPlayerCards)
    game.playDealer(ResultView::printDealerHit)
}

private fun printGameResults(game: Game) {
    ResultView.printPlayerResult(game.players, game.dealer)
    ResultView.printGameProfits(game.profits())
}

