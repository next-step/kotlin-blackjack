package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.GameManager
import blackjack.domain.card.PlayingCards
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
        val cards = PlayingCards.shuffle(RandomShuffleStrategy())
        val deck = Deck(cards.toMutableList())
        val names = InputFilter.inputPlayer()
        val bets = names.map { InputFilter.inputBettingMoney(it.toString()) }.toTypedArray()
        val dealer = Dealer(deck.getCards(NUMBER_OF_STARTING_CARDS))
        val players = Participants.createPlayers(names, deck, bets)

        printPlayerNames(players)
        printParticipantsCards(players + dealer)
        val newPlayers = Participants(players.getPlayers().map { playPlayer(it, deck) })
        val newDealer = playDealer(dealer, deck)
        printFinalResult(newPlayers + newDealer)
    }

    private fun playPlayer(player: Role, deck: Deck): Role {
        var newPlayer = player
        while (InputFilter.inputHitOrStay(newPlayer.name.toString()) && canPlayerHit(newPlayer)) {
            newPlayer = GameManager.hit(newPlayer, deck)
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        if (newPlayer.hasOnlyTwoCards) {
            ResultView.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        return GameManager.stay(newPlayer)
    }

    private fun playDealer(dealer: Dealer, deck: Deck): Role {
        if (GameManager.canDealerHit(dealer)) {
            ResultView.printDealerDrawMessage()
            return dealer.draw(deck.getCard())
        }
        return dealer.stay()
    }

    private fun canPlayerHit(player: Role): Boolean {
        if (player.isBust) {
            ResultView.printPlayerBust(player.name.toString())
            return false
        }
        if (player.isBlackjack) {
            ResultView.printPlayerBlackjack(player.name.toString())
            return false
        }
        return true
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
