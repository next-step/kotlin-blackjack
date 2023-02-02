package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.result.Result
import blackjack.domain.participant.state.role.Dealer
import blackjack.domain.participant.state.role.Role
import blackjack.dto.Input
import blackjack.dto.Output
import blackjack.dto.ParticipantDto
import blackjack.dto.ParticipantsDto
import blackjack.dto.ResultDto
import blackjack.dto.ResultsDto

class GameManager(private val input: Input, private val output: Output) {
    fun prepare(deck: Deck): Participants {
        val names = input.inputPlayer()
        val bets = names.map { input.inputBettingMoney(it.toString()) }.toTypedArray()
        val players = Participants.createPlayers(names, deck, bets)
        val dealer = Dealer(deck.getCards(Role.NUMBER_OF_STARTING_CARDS))

        output.printGameStartMessage(names.map { it.toString() })
        output.printParticipantsCards(ParticipantsDto.from(players + dealer))

        return players + dealer
    }

    fun play(players: Participants, deck: Deck) {
        val newPlayers = players.getPlayers().map { player ->
            inputPlayersHitOrStay(player, deck)
        }
        val newDealer = playDealer(players.getDealer(), deck)
        val participants = Participants(newPlayers) + newDealer
        output.printResultWithScore(ParticipantsDto.from(participants))

        val results = Result.calculateProfit(participants)
        val resultsDto = results.map { ResultDto.from(it.key, it.value.toInt()) }
        output.printResult(ResultsDto(resultsDto))
    }

    private fun playDealer(dealer: Dealer, deck: Deck): Role {
        if (dealer.score <= Dealer.STOP_SCORE) {
            output.printDealerDrawMessage()
            return dealer.draw(deck.getCard())
        }
        return dealer.stay()
    }

    private fun inputPlayersHitOrStay(player: Role, deck: Deck): Role {
        var newPlayer = player
        while (input.inputHitOrStay(newPlayer.name.toString()) && canPlayerHit(newPlayer)) {
            newPlayer = playPlayer(newPlayer, deck)
        }
        if (newPlayer.hasOnlyTwoCards) {
            output.printParticipantCards(ParticipantDto.from(newPlayer))
        }
        return newPlayer.stay()
    }

    private fun playPlayer(player: Role, deck: Deck): Role {
        val newPlayer = player.draw(deck.getCard())
        output.printParticipantCards(ParticipantDto.from(newPlayer))
        return newPlayer
    }

    private fun canPlayerHit(player: Role): Boolean {
        if (player.isBust) {
            output.printPlayerBust(player.name.toString())
            return false
        }
        if (player.isBlackjack) {
            output.printPlayerBlackjack(player.name.toString())
            return false
        }
        return true
    }
}
