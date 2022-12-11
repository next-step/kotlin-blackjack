package com.nextstep.blackjack.controller

import com.nextstep.blackjack.domain.Deck
import com.nextstep.blackjack.domain.Player
import com.nextstep.blackjack.view.InputView
import com.nextstep.blackjack.view.InputView.CONTINUE
import com.nextstep.blackjack.view.InputView.STOP
import com.nextstep.blackjack.view.OutputView

fun main() {
    OutputView.printStartMessage()

    val playersInput = InputView.inputMessageSplitWithComma()
    val players = playersInput.map { Player(it) }
    val deck = Deck.createDeck()

    setInitialState(players, deck)

    OutputView.printInitialStateMessage(players)

    play(players, deck)

    OutputView.printPlayerStatusMessage(players)
}

private fun play(
    players: List<Player>,
    deck: Deck
) {
    players.forEach {
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
}

private fun setInitialState(
    players: List<Player>,
    deck: Deck
) {
    players.forEach {
        it.draw(deck.initialDraw())
    }
}
