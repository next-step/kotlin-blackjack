package blackjack.dto

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
            val total = player.calculateTotal()
            "${player.getName()}: ${player.getAllCards().joinToString(", ") { it.display() }} - $total"
        }
    }
}
