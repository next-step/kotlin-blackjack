package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.holder.Player
import blackjack.domain.value.BettingAmount
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {

    val names = InputView.askNames()
    val players = names.map {
        val amount = InputView.askBettingAmount(it)
        Player(name = it, bettingAmount = BettingAmount(amount))
    }

    val game = BlackJackGame(players)
    val playerDtos = game.start()

    OutputView.printFirstDeal(names)
    OutputView.printCardsInit(playerDtos)


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
            val addCardResult = game.addCard(name)
            OutputView.printCardsAdded(addCardResult)
            moreCard = !addCardResult.bust
        }
    } while (moreCard)
}
