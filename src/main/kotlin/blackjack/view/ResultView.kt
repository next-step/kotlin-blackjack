package blackjack.view

import blackjack.domain.user.Player

interface ResultView {

    fun printPlayerCards(players: Player)

    fun printResult(players: Player)

    fun printFinalResult(players: Player)

    fun printDealerDrawCardAlert(dealerDrawThresholdPoint: Int)
}
