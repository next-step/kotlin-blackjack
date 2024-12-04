package blackjack.dto

import blackjack.domain.Participants

class ParticipantsResponse(private val participants: Participants) {
    fun toFormattedStringPlayerNames(): String {
        return participants.players.joinToString(", ") { player -> player.getName() }
    }

    fun toFormattedStringDealerInitialCard(): String {
        val dealer = participants.dealer
        return "${dealer.getDealerName()}카드: ${dealer.getFirstCard()}"
    }

    fun toFormattedStringPlayerCards(): String {
        return participants.players.joinToString("\n") { player ->
            "${player.getName()}: ${player.displayHand()}"
        }
    }

    fun toFormattedStringPlayerResults(): String {
        return participants.getAllParticipants().joinToString("\n") { player ->
            val total = player.calculateTotal()
            "${player.getName()}: ${player.displayHand()} - $total"
        }
    }
}
