package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameTable
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackGame(
    private val inputView: InputView,
    private val resultView: ResultView,
) {
    fun start() {
        val gameTable = GameTable(Deck.create())
        val participants = playGame(gameTable)
        printGameResult(participants)
    }

    private fun playGame(gameTable: GameTable): List<Participant> {
        val participants = setUpInitCard(gameTable)
        val (players, dealer) = Participant.separate(participants)
        val gamedPlayers = playersTurn(players, gameTable)
        resultView.linebreak()
        val gamedDealer = dealerTurn(dealer, gameTable)
        return gamedPlayers + gamedDealer
    }

    private fun setUpInitCard(gameTable: GameTable): List<Participant> {
        val participants = gameTable.dealInitCard(getParticipants())
        resultView.linebreak()
        resultView.printInitCardReceive(participants)
        resultView.printParticipantsCard(participants = participants, printScore = false)
        resultView.linebreak()
        return participants
    }

    private fun playersTurn(
        participants: List<Participant>,
        gameTable: GameTable,
    ): List<Participant> {
        return participants.map { playerTurn(it, gameTable) }
    }

    private tailrec fun playerTurn(
        player: Participant,
        gameTable: GameTable,
    ): Participant {
        if (!player.canHit() || !inputView.inputHit(player)) {
            return player
        }
        val hitPlayer = gameTable.hit(player)
        resultView.printParticipantCard(participant = player, printScore = false)
        return playerTurn(hitPlayer, gameTable)
    }

    private tailrec fun dealerTurn(
        dealer: Participant,
        gameTable: GameTable,
    ): Participant {
        if (!dealer.canHit()) {
            return dealer
        }
        resultView.printDealerHit()
        return dealerTurn(gameTable.hit(dealer), gameTable)
    }

    private fun printGameResult(participants: List<Participant>) {
        resultView.linebreak()
        resultView.printParticipantsCard(participants = participants, printScore = true)
    }

    private fun getParticipants(): List<Participant> {
        return buildList {
            add(Dealer.create())
            addAll(inputView.inputNames().map { Player.create(name = it) })
        }
    }
}
