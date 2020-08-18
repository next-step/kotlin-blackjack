package blackJack

import blackJack.domain.BlackJack
import blackJack.domain.Dealer
import blackJack.domain.Player
import blackJack.view.InputView
import blackJack.view.ResultView

var canGetCard = true

fun main() {
    try {
        startGame()
    } catch (e: Exception) {
        ResultView.resultError(e.message)
    }
}

fun startGame() {
    val names = InputView.inputPlayer()
    val blackJack = BlackJack(names)
    ResultView.resultReady(blackJack)
    val dealer = blackJack.dealer
    blackJack.playersForEach { playerCheckBust(it, dealer) }
    ResultView.resultOpenDealerCard(dealer)
    dealerGetCard(dealer)
    ResultView.resultGame(blackJack)
}

fun playerCheckBust(player: Player, dealer: Dealer) {
    canGetCard = true
    while (!player.isBust() && canGetCard) {
        playerWhetherGet(player, dealer)
    }
}

fun playerWhetherGet(player: Player, dealer: Dealer) {
    val inputValue = InputView.inputWhether(player)
    if (inputValue == "y") {
        dealer.giveCard(player)
        ResultView.resultWhetherBust(player)
    }
    if (inputValue == "n") {
        ResultView.resultPeopleHands(player)
        canGetCard = false
    }
}

fun dealerGetCard(dealer: Dealer) {
    dealer.takeCard()
    if (dealer.getHandsSize() > 2) {
        ResultView.resultDealerGetCard(dealer)
    }
}
