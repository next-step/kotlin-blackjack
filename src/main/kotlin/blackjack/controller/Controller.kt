package blackjack.controller

import blackjack.domain.GameManager
import blackjack.domain.card.Deck
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.result.Result.Companion.calculateProfit
import blackjack.domain.participant.state.role.Dealer
import blackjack.domain.participant.state.role.Role
import blackjack.domain.participant.state.role.Role.Companion.NUMBER_OF_STARTING_CARDS
import blackjack.dto.ParticipantDto
import blackjack.dto.ParticipantsDto
import blackjack.dto.ResultDto
import blackjack.dto.ResultsDto
import blackjack.view.ResultView

object Controller {
    private val printDealerDrawMessage = { ResultView.printDealerDrawMessage() }
    private val printParticipantCards = { players: Role -> ResultView.printParticipantCards(ParticipantDto.from(players)) }
    private val printPlayerBust = { player: Role -> ResultView.printPlayerBust(player.name.toString()) }
    private val printPlayerBlackjack = { player: Role -> ResultView.printPlayerBlackjack(player.name.toString()) }

    fun start() {
        val deck = Deck(RandomShuffleStrategy())
        val names = InputFilter.inputPlayer()
        val bets = names.map { InputFilter.inputBettingMoney(it.toString()) }.toTypedArray()
        val dealer = Dealer(deck.getCards(NUMBER_OF_STARTING_CARDS))
        val players = Participants.createPlayers(names, deck, bets)

        printPlayerNames(players)
        printParticipantsCards(players + dealer)
        val newPlayers = Participants(players.getPlayers().map { inputPlayersHitOrStay(it, deck) })
        val newDealer = GameManager.playDealer(dealer, deck, printDealerDrawMessage)
        printFinalResult(newPlayers + newDealer)
    }

    private fun inputPlayersHitOrStay(player: Role, deck: Deck): Role {
        var newPlayer = player
        while (InputFilter.inputHitOrStay(newPlayer.name.toString()) &&
            GameManager.canPlayerHit(newPlayer, printPlayerBust, printPlayerBlackjack)
        ) {
            newPlayer = GameManager.playPlayer(newPlayer, deck, printParticipantCards)
        }
        if (newPlayer.hasOnlyTwoCards) {
            printParticipantCards(newPlayer)
        }
        return GameManager.stay(newPlayer)
    }

    private fun printPlayerNames(players: Participants) {
        ResultView.printGameStartMessage(ParticipantsDto.from(players).getNames())
    }

    private fun printParticipantsCards(participants: Participants) {
        ParticipantsDto.from(participants).participants.forEach {
            ResultView.printParticipantCards(it)
        }
        ResultView.printLineFeed()
    }

    private fun printFinalResult(participants: Participants) {
        ResultView.printLineFeed()
        participants.getAll().forEach {
            ResultView.printResultWithScore(ParticipantDto.from(it))
        }

        ResultView.printLineFeed()
        val results = calculateProfit(participants)
        val resultsDto = results.map { ResultDto.from(it.key, it.value.toInt()) }
        ResultView.printResult(ResultsDto(resultsDto))
    }
}
