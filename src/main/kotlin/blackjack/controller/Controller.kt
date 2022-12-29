package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Participants.Companion.NUMBER_OF_INIT_CARDS
import blackjack.domain.participant.state.result.Result.Companion.calculateResult
import blackjack.domain.participant.state.role.Role
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

        printPlayerNames(players)
        printParticipantsCards(players.plus(dealer))
        val newPlayers = doPlayerHitOrStay(players, deck)
        val newDealer = doDealerHitOrStay(dealer, deck)
        printFinalResult(newPlayers.plus(newDealer))
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

    private fun doPlayerHitOrStay(players: Participants, deck: Deck): Participants {
        if (players.isBlackjack()) {
            return players
        }
        return Participants(players.getPlayers().map { doHitOrStay(it, deck) })
    }

    private fun doHitOrStay(role: Role, deck: Deck): Role {
        var newPlayer = role
        while (InputFilter.inputHitOrStay(ParticipantDto.from(role).name)) {
            newPlayer = role.draw(deck.getCard())
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        if (!newPlayer.isBust() && !newPlayer.isBlackjack()) {
            newPlayer = role.stay()
        }
        if (newPlayer.getCardsSize() == NUMBER_OF_INIT_CARDS) {
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        return newPlayer
    }

    private fun doDealerHitOrStay(role: Role, deck: Deck): Role {
        if (role.getScore() <= Dealer.STOP_SCORE) {
            ResultView.printDealerDrawMessage()
            return role.draw(deck.getCard())
        }
        return role.stay()
    }

    private fun printFinalResult(participants: Participants) {
        ResultView.printLineFeed()
        participants.getAll().forEach {
            ResultView.printResultWithScore(ParticipantDto.from(it))
        }

        ResultView.printLineFeed()
        val results = calculateResult(participants)
        val resultsDto = results.map { ResultDto.from(it.key, it.value) }
        ResultView.printResult(ResultsDto(resultsDto))
    }
}
