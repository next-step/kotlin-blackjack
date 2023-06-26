package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.domain.player.PlayerImpl
import blackjack.view.InputView
import blackjack.view.OutputView

object GameController {
    private const val DEFAULT_INITIAL_DRAW = 2

    fun run() {
        val deck = Deck()
        val players = prepareGame(deck)
        printBeforeStart(players)
        playGame(players, deck)
        endGame(players)
    }

    private fun prepareGame(deck: Deck): Participants {
        val playerImpls = InputView.getPlayerNames().map { PlayerImpl(it) }
        val dealer = Dealer()
        val participants = Participants(playerImpls, dealer)

        repeat(DEFAULT_INITIAL_DRAW) {
            participants.drawAll(deck)
        }
        return participants
    }

    private fun printBeforeStart(participants: Participants) {
        OutputView.printGameStart(participants.players.map { it.name }, DEFAULT_INITIAL_DRAW)
        OutputView.printCard(participants)
    }

    private fun playGame(participants: Participants, deck: Deck) {
        participants.players.forEach { playTurn(it, deck) }
        if (participants.dealer.canDraw()) {
            participants.dealer.drawCard(deck)
        }
    }

    private fun playTurn(playerImpl: PlayerImpl, deck: Deck) {
        while (playerImpl.canDraw() && InputView.askDrawCard(playerImpl)) {
            playerImpl.drawCard(deck)
            OutputView.printPlayerCard(playerImpl)
        }
    }

    private fun endGame(participants: Participants) {
        OutputView.printPlayerResult(participants)
    }
}
