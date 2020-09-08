package blackJack

import blackJack.domain.BlackJack
import blackJack.domain.Dealer
import blackJack.domain.Player
import blackJack.view.InputView
import blackJack.view.ResultView

fun main() {
    try {
        startApp()
    } catch (e: Exception) {
        ResultView.resultError(e.message)
    }
}

fun startApp() {
    val players = InputView.playerNames()
    val blackJack = BlackJack(players)
    val dealer = blackJack.dealer
    blackJack.bettingMoney { InputView.bettingMoney(it) }
    blackJack.readyGame()
    ResultView.resultReady(blackJack)
    blackJack.hitOrStay { whileNotBust(it, dealer) }
    ResultView.resultOpenDealerCard(dealer)
    blackJack.getDealerCard { ResultView.resultDealerGetCard(it) }
    ResultView.resultGame(blackJack)
}

fun whileNotBust(player: Player, dealer: Dealer) {
    var isHit = true
    while (isHit && !player.isBust()) {
        isHit = hitOrStay(player, dealer)
        ResultView.resultWhetherBust(player)
    }
}

fun hitOrStay(player: Player, dealer: Dealer): Boolean {
    val inputValue = InputView.hitOrStay(player)
    if (inputValue == "y") {
        dealer.giveCard(player)
        return true
    }
    return false
}
