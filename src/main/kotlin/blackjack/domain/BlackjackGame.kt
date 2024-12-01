package blackjack.domain

import blackjack.domain.betting.Betting
import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckGenerator
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.view.dto.CreatePlayersDto
import blackjack.view.dto.ParticipantDto
import blackjack.view.dto.ParticipantsDto
import blackjack.view.dto.RecordDto

class BlackjackGame(
    players: List<CreatePlayersDto>,
    deckGenerator: DeckGenerator,
) {
    private val dealer: Dealer = Dealer()
    private val deck: Deck = deckGenerator.generate()
    private val participants =
        Participants(listOf(dealer) + players.map { Player(name = it.name, betting = Betting(it.betting)) })

    fun start() {
        participants.participants.forEach { player ->
            repeat(CARD_DRAW_COUNT_GAME_START) { player.receiveCard(deck.draw()) }
        }
    }

    fun canDraw(name: String): Boolean {
        val player = participants.findPlayer(name)
        return player.canReceive()
    }

    fun dealCard(name: String) {
        val player = participants.findPlayer(name)
        player.receiveCard(deck.draw())
    }

    fun dealCardToDealer() {
        if (dealer.canReceive()) {
            dealer.receiveCard(deck.draw())
        }
    }

    fun getParticipants(): ParticipantsDto {
        return ParticipantsDto.from(participants)
    }

    fun getParticipant(name: String): ParticipantDto {
        return ParticipantDto.from(participants.findPlayer(name))
    }

    fun settle(): List<RecordDto> {
        val players = participants.participants.filterIsInstance<Player>()
        val records =
            players.map {
                val rate = it.calculateRate(dealer)
                RecordDto(
                    name = it.name,
                    profit = it.betting.applyRate(rate),
                )
            }
        val dealerProfit = records.filter { it.profit < 0 }.map { it.profit }.sumOf { it * (-1) }

        return listOf(RecordDto(name = dealer.name, profit = dealerProfit)) + records
    }

    companion object {
        private const val CARD_DRAW_COUNT_GAME_START = 2
    }
}
