package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.GameManager
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.participant.ParticipantFactory
import blackjack.domain.participant.Participants
import blackjack.dto.ParticipantDto
import blackjack.dto.ParticipantsDto
import blackjack.dto.ResultDto
import blackjack.dto.ResultsDto
import blackjack.view.ResultView

object Controller {
    fun start() {
        val cards = PlayingCards.shuffle(RandomShuffleStrategy())
        val deck = Deck(cards.toMutableList())
        val names = InputFilter.inputPlayer()
        val participants = ParticipantFactory.create(names, deck)

        init(participants)
        if (GameManager.checkBlackjack(participants)) {
            end(participants)
            return
        }
        val newPlayers = GameManager.play(participants, deck)
        end(newPlayers)
    }

    private fun init(participants: Participants) {
        val players = Participants(participants.getPlayers())
        ResultView.printGameStartMessage(ParticipantsDto.from(players).getNames())
        ParticipantsDto.from(participants).players.forEach {
            ResultView.printParticipantCards(it)
        }
        ResultView.printLineFeed()
    }

    private fun end(participants: Participants) {
        ResultView.printLineFeed()
        participants.getAll().forEach {
            ResultView.printResultWithScore(ParticipantDto.from(it))
        }

        ResultView.printLineFeed()
        val results = GameManager.calculateResult(participants)
        val resultsDto = results.map { ResultDto.from(it.key, it.value) }
        ResultView.printResult(ResultsDto(resultsDto))
    }
}
