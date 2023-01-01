package com.nextstep.blackjack

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
    outputView.printStatus(players)

}
