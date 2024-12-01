package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckGenerator
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.view.dto.ParticipantDto
import blackjack.view.dto.ParticipantsDto
import blackjack.view.dto.RecordDto

class BlackjackGame(
    names: List<String>,
    deckGenerator: DeckGenerator,
) {
    private val dealer: Dealer = Dealer()
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

    fun getRecords(): RecordDto {
        return RecordDto.of(dealer, participants)
    }

    companion object {
        private const val CARD_DRAW_COUNT_GAME_START = 2
    }
}
