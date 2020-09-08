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
    blackJack.bettingPlayers { it.bettingMoney(InputView.bettingMoney(it)) }
    ResultView.resultReady(blackJack)
    blackJack.players.forEach { playerWhetherGet(it, blackJack) }
    openDealerCard(blackJack.dealer)
    ResultView.resultGame(blackJack)
}

fun playerWhetherGet(player: Player, blackJack: BlackJack) {
    do {
        val inputValue = InputView.hitOrStay(player)
        val isHit = inputValue == "y"
        blackJack.giveCard(isHit, player)
        viewResultBust(isHit, player)
    } while (!player.isBust() && isHit)
}

fun viewResultBust(isHit: Boolean, player: Player) {
    if (isHit) {
        ResultView.resultWhetherBust(player)
    } else {
        ResultView.resultPeopleHands(player)
    }
}

fun openDealerCard(dealer: Dealer) {
    ResultView.resultOpenDealerCard(dealer)
    while (dealer.canAddCard()) {
        dealer.giveCard(dealer)
        ResultView.resultDealerGetCard(dealer)
    }
}
