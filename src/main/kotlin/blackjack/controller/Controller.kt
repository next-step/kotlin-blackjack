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
    fun start() {
        val deck = Deck(RandomShuffleStrategy())
        val names = InputFilter.inputPlayer()
        val bets = names.map { InputFilter.inputBettingMoney(it.toString()) }.toTypedArray()
        val players = Participants.createPlayers(names, deck, bets)
        val dealer = Dealer(deck.getCards(NUMBER_OF_STARTING_CARDS))
        val gameManager = GameManager(
            askHit = { player: Role -> InputFilter.inputHitOrStay(player.name.toString()) },
            printParticipantCards = { player: Role -> ResultView.printParticipantCards(ParticipantDto.from(player)) },
            printPlayerBust = { player: Role -> ResultView.printPlayerBust(player.name.toString()) },
            printPlayerBlackjack = { player: Role -> ResultView.printPlayerBlackjack(player.name.toString()) },
            printDealerDrawMessage = { ResultView.printDealerDrawMessage() }
        )
        printPlayerNames(players)
        printParticipantsCards(players + dealer)
        printFinalResult(gameManager.play(players + dealer, deck))
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
