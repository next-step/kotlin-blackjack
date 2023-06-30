package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import blackjack.domain.player.Participants
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
        showResult(players)
    }

    private fun prepareGame(deck: Deck): Participants {
        val gamePlayers = InputView.getPlayerNames().map { GamePlayer(it) }
        val dealer = Dealer()
        val participants = Participants(gamePlayers, dealer)

        // 베팅금액
        gamePlayers.forEach { player ->
            val amount = InputView.askBettingAmount(player.name)
            dealer.getMoneyFromPlayer(player, amount)
        }

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
            OutputView.printDealerGetAdditionalCard()
            participants.dealer.drawCard(deck)
        }
    }

    private fun playTurn(gamePlayer: GamePlayer, deck: Deck) {
        while (gamePlayer.canDraw() && InputView.askDrawCard(gamePlayer)) {
            gamePlayer.drawCard(deck)
            OutputView.printPlayerCard(gamePlayer)
        }
    }

    private fun endGame(participants: Participants) {
        OutputView.printPlayerResult(participants)
    }

    private fun showResult(participants: Participants) {
        participants.finishGame()
        OutputView.showWinner(participants.allGameMembers)
    }
}
