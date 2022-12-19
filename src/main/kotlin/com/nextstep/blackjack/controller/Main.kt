package com.nextstep.blackjack.controller

import com.nextstep.blackjack.domain.Deck
import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.domain.Players
import com.nextstep.blackjack.view.InputView
import com.nextstep.blackjack.view.InputView.CONTINUE
import com.nextstep.blackjack.view.InputView.STOP
import com.nextstep.blackjack.view.OutputView

fun main() {
    OutputView.printStartMessage()

    val playersInput = InputView.inputMessageSplitWithComma()
    val players = Players(playersInput.map(::Player))
    val deck = Deck.createDeck()

    players.initState(deck)

    OutputView.printInitialStateMessage(players)

    players.players.forEach {
        while (!it.isBust()) {
            OutputView.printOngoingMessage(it.name)
            val continueInput = InputView.inputMessage()
            if (continueInput == CONTINUE) {
                it.draw(listOf(deck.draw()))
                OutputView.printPlayerOngoingStatusMessage(it)
            }
            if (continueInput == STOP) {
                break
            }
        }
    }

    OutputView.printPlayerStatusMessage(players)
}
