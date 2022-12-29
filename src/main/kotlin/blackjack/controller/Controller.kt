package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.GameManager
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Participants.Companion.NUMBER_OF_INIT_CARDS
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
        val dealer = Dealer(deck.getCards(NUMBER_OF_INIT_CARDS))
        val players = Participants.createPlayers(names, deck)
        val participants = players.plus(dealer)

        init(participants)
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
