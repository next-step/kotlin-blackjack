package game.blackjack.domain

class Participants(private val participants: List<Participant>) {

    fun distributeInitialCards(deck: Deck) {
        participants.forEach { it.drawCard(deck.initialDraw()) }
    }

    fun drawAdditionalCards(
        deck: Deck,
        drawDecision: (Participant) -> Boolean,
        result: (Participant) -> Unit
    ) {
        participants.forEach { participant ->
            while (participant.isNotBust() && drawDecision(participant)) {
                participant.drawCard(deck.drawOnce())
                result(participant)
            }
        }
    }

    fun toNames(): String {
        return participants.joinToString(", ") { it.name }
    }

    fun toFinalResult(): String {
        return participants.joinToString("\n") { it.toString() + " - 결과: ${it.getScore()}" }
    }

    override fun toString(): String {
        return participants.joinToString("\n") { it.toString() }
    }
}
