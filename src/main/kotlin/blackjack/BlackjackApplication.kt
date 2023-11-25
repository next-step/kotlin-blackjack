package blackjack

import blackjack.domain.BettingMoney
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.ProfitCalculator
import blackjack.domain.RandomDeck
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val dealer = Dealer(RandomDeck.from())
    val inputNames = InputView.inputNames()
    val inputNameAndBets = InputView.inputBets(inputNames)
    val players = Players.init(dealer, inputNames)

    players.initCard()
    ResultView.printInitPlayers(players)

    players.players
        .forEach { play(dealer, it) }
    dealerPlay(dealer)
    ResultView.printCardResult(players)

    printGameResult(players)
    printProfit(players, inputNameAndBets)
}

private fun play(dealer: Dealer, player: Player) {
    while (player.canHit()) {
        hitOrStand(dealer, player)
    }
}

private fun hitOrStand(dealer: Dealer, player: Player) {
    when (InputView.inputHitOrStand(player)) {
        true -> {
            val card = dealer.draw()
            player.hit(card)
            ResultView.printPlayerNameAndCard(player)
        }

        false -> player.stand()
    }
}

private fun dealerPlay(dealer: Dealer) {
    while (dealer.canHit()) {
        val card = dealer.draw()
        dealer.hit(card)
        ResultView.printDealerHitMessage()
    }
}

private fun printGameResult(players: Players) {
    val dealerResult = players.getDealerResult()
    val playersResult = players.getPlayersResult()
    ResultView.printGameResult(dealerResult, playersResult)
}

private fun printProfit(players: Players, inputNameAndBets: Map<String, BettingMoney>) {
    val profitCalculator = ProfitCalculator(inputNameAndBets)
    val dealerProfit = players.getDealerProfit(profitCalculator)
    val playersProfit = players.getPlayersProfit(profitCalculator)
    ResultView.printProfit(dealerProfit, playersProfit)
}
