package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckGenerator
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.view.dto.ParticiapntsDto
import blackjack.view.dto.ParticipantDto
import blackjack.view.dto.RecordDto

class BlackjackGame(
    private val dealer: Dealer,
    names: List<String>,
    deckGenerator: DeckGenerator,
) {
    private val deck: Deck = deckGenerator.generate()
    private val participants = Participants(listOf(dealer) + names.map { Player(name = it) })

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

    fun getRecords(): RecordDto {
        val players = participants.participants.filterIsInstance<Player>()
        return RecordDto(
            dealerName = dealer.name,
            records = players.map { it.name to it.isWin(dealer) }.toMap(),
        )
    }

    fun getParticipants(): ParticiapntsDto {
        return Participants.toDto(participants)
    }

    fun getParticipant(name: String): ParticipantDto {
        return Participant.toDto(participants.findPlayer(name))
    }

    companion object {
        private const val CARD_DRAW_COUNT_GAME_START = 2
    }
}
