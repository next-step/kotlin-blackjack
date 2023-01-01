package com.nextstep.blackjack

import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.domain.Players
import com.nextstep.blackjack.domain.card.CardDeck
import com.nextstep.blackjack.view.InputView
import com.nextstep.blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val names = inputView.inputNames()
    val players = Players(names)
    val cards = CardDeck()

    players.dealCards(cards)
    players.dealCards(cards)

    outputView.printAfterDealing(players)
    outputView.printStatus(*players.players.toTypedArray())

    for (player in players.players) {
        while (needMoreCard(player, inputView)) {
            player.addCard(cards.draw())
            outputView.printStatus(player)
        }
    }

    outputView.printResult(players.players)
}

fun needMoreCard(player: Player, inputView: InputView): Boolean {
    if (player.calculateScore() < 21) {
        return inputView.askMoreCard(player.name) == "y"
    }
    return false
}
