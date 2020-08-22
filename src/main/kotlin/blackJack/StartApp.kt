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
    blackJack.playersForEach { playerCheckBust(it, blackJack) }
    ResultView.resultOpenDealerCard(dealer)
    dealerGetCard(dealer)
    ResultView.resultGame(blackJack)
}

fun playerCheckBust(player: Player, blackJack: BlackJack) {
    canGetCard = true
    while (!player.isBust() && canGetCard) {
        playerWhetherGet(player, blackJack)
    }
}

fun playerWhetherGet(player: Player, blackJack: BlackJack) {
    val inputValue = InputView.inputWhether(player)
    canGetCard = blackJack.playerGetCard(player, inputValue)
        ?: throw IllegalArgumentException("$inputValue 에 대한 요청은 없습니다. y 또는 n 으로 답해주세요")
    ResultView.resultWhetherBust(player)
}

fun dealerGetCard(dealer: Dealer) {
    dealer.takeCard()
    if (dealer.getHandsSize() > 2) {
        ResultView.resultDealerGetCard(dealer)
    }
}
