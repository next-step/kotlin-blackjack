package blackJack

import blackJack.domain.BlackJack
import blackJack.domain.Dealer
import blackJack.domain.Player
import blackJack.view.InputView
import blackJack.view.ResultView

var isGetCard = true

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
    blackJack.players.forEach { playerCheckBust(it, dealer) }
    ResultView.resultOpenDealerCard(dealer)
    dealerGetCard(dealer)
    ResultView.resultGame(blackJack)
}

fun playerCheckBust(player: Player, dealer: Dealer) {
    isGetCard = true
    while (!player.isBust() && isGetCard) {
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
        ResultView.blank()
        isGetCard = false
    }
}

fun dealerGetCard(dealer: Dealer) {
    while (!dealer.isOver16()) {
        dealer.giveCard(dealer)
        ResultView.resultDealerGetCard(dealer)
    }
}
