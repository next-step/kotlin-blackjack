package com.nextstep.jngcii.blackjack

import com.nextstep.jngcii.blackjack.domain.CardDeck
import com.nextstep.jngcii.blackjack.domain.GameRunner
import com.nextstep.jngcii.blackjack.domain.PlayerBoard
import com.nextstep.jngcii.blackjack.view.InputView
import com.nextstep.jngcii.blackjack.view.ResultView

fun main() {
    val names = InputView.getNames(::readLine)

    val cardDeck = CardDeck()
    val boards = names.map { PlayerBoard(it) }

    boards.forEach {
        it.ready(cardDeck)
    }
    ResultView.printReady(boards)

    boards.forEach {
        GameRunner.run(cardDeck, it, InputView::getMoreable, ResultView::printPlayerState)
    }

    ResultView.printResult(boards)
}

const val INITIAL_COUNT = 2
