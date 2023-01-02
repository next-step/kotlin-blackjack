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

    cards.deal(*players.players)
    cards.deal(*players.players)

    outputView.printAfterDealing(players)
    outputView.printStatus(*players.players)

    for (player in players.players) {
        while (needMoreCard(player, inputView)) {
            cards.deal(player)
            outputView.printStatus(player)
        }
    }

    outputView.printResult(*players.players)
}

fun needMoreCard(player: Player, inputView: InputView): Boolean {
    if (player.calculateScore() < 21) {
        return inputView.askMoreCard(player.name) == "y"
    }
    return false
}
