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
        val gameTable = GameTable(getParticipants(), Deck.create())
        val participants = playGame(gameTable)
        printGameResult(participants)
    }

    private fun getParticipants(): List<Participant> {
        return buildList {
            add(Dealer.create())
            addAll(inputView.inputNames().map { Player.create(name = it) })
        }
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
        val participants = gameTable.dealInitCard()
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

    private fun playerTurn(
        player: Participant,
        gameTable: GameTable,
    ): Participant {
        return if (player.canHit() && inputView.inputHit(player)) {
            val hitPlayer = gameTable.hit(player)
            resultView.printParticipantCard(participant = player, printScore = false)
            playerTurn(hitPlayer, gameTable)
        } else {
            player
        }
    }

    private fun dealerTurn(
        dealer: Participant,
        gameTable: GameTable,
    ): Participant {
        return if (dealer.canHit()) {
            resultView.printDealerHit()
            dealerTurn(gameTable.hit(dealer), gameTable)
        } else {
            dealer
        }
    }

    private fun printGameResult(participants: List<Participant>) {
        resultView.linebreak()
        resultView.printParticipantsCard(participants = participants, printScore = true)
    }
}
