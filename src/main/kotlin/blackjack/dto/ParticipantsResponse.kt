package blackjack.dto

import blackjack.domain.Card
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
            val cards = player.getAllCards()

            val scoreDisplay = formattedStringSpecificCase(cards, total)

            "${player.getName()}: ${cards.joinToString(", ") { it.display() }} - $scoreDisplay"
        }
    }

    private fun formattedStringSpecificCase(
        cards: List<Card>,
        total: Int,
    ) = when {
        cards.size == 2 && total == 21 -> "Blackjack"
        total == 0 -> "Bust"
        else -> total.toString()
    }
}
