package com.nextstep.jngcii.blackjack

import com.nextstep.jngcii.blackjack.domain.CardDeck
import com.nextstep.jngcii.blackjack.domain.PlayerBoard
import com.nextstep.jngcii.blackjack.view.InputView

fun main() {
    val names = InputView.getNames(::readLine)

    val cardDeck = CardDeck()
    val boards = names.map { PlayerBoard(it) }
}
