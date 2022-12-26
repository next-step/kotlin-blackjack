package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {

    val names = InputView.askNames()

    val game = BlackJackGame(names.map { Player(it) }, Dealer(), CardDeck())
    val playerDtos = game.start()

    OutputView.printFirstDeal(names)
    OutputView.printCardsByPlayer(playerDtos)


    names.forEach {
        dealByPlayer(it, game)
    }
    val hitCount = game.hitDealer()

    OutputView.printHitDealerResult(hitCount)
    OutputView.printResult(game.result())
}

private fun dealByPlayer(name: String, game: BlackJackGame) {
    do {
        var moreCard: Boolean = InputView.askMoreCard(name)
        if (moreCard) {
            val playerDto = game.addCard(name)
            OutputView.printCardsByPlayer(playerDto)
            moreCard = !playerDto.bust()
        }
    } while (moreCard)
}
