package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Score
import blackjack.view.getOrderFrom
import blackjack.view.getPlayerNames
import blackjack.view.printCardsServedFirstToPlayers
import blackjack.view.printPlayerCards
import blackjack.view.printResult

fun main() {
    val dealer = Dealer.makeUpGame()
    val players = getPlayerNames().map(::Player)

    players.forEach(dealer::giveTwoCardsTo)
    printCardsServedFirstToPlayers(players)

    players.forEach { askWantToTakeOneMoreCard(it, dealer) }
    printResult(players)
}

fun askWantToTakeOneMoreCard(player: Player, dealer: Dealer) =
    when (player.score < Score.TWENTY_ONE && getOrderFrom(player)) {
        true -> takeOneMoreCard(player, dealer)
        false -> Unit
    }

fun takeOneMoreCard(player: Player, dealer: Dealer) {
    dealer.giveOneMoreCardTo(player)
    printPlayerCards(player)
    askWantToTakeOneMoreCard(player, dealer)
}
