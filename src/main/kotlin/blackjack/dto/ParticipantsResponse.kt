package blackjack.dto

import blackjack.domain.Participant
import blackjack.domain.Participants

class ParticipantsResponse(private val participants: Participants) {
    fun toFormattedStringPlayerNames(): String {
        return participants.players.joinToString(", ") { player -> player.getName() }
    }

    fun toFormattedStringInitialParticipantsCard(): String {
        return participants.getAllParticipants().joinToString("\n") { participant ->
            "${participant.getName()}: ${participant.getInitialCard().joinToString(", ") { it.display() }}"
        }
    }

    fun toFormattedStringPlayerResults(): String {
        return participants.getAllParticipants().joinToString("\n") { player ->
            val cards = player.getAllCards()
            val scoreDisplay = formattedStringSpecificCase(player)

            "${player.getName()}: ${cards.joinToString(", ") { it.display() }} - $scoreDisplay"
        }
    }

    private fun formattedStringSpecificCase(participant: Participant) =
        when {
            participant.isBlackjack() -> "Blackjack"
            participant.isBust() -> "Bust"
            else -> participant.calculateTotal().toString()
        }
}
