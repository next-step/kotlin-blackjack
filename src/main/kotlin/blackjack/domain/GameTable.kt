package blackjack.domain

data class GameTable(
    private val deck: Deck,
) {
    fun dealInitCard(participants: List<Participant>): List<Participant> {
        return participants.map { participant ->
            (1..INIT_CARD_DRAW_COUNT).fold(participant) { acc, _ ->
                acc.hit(deck.draw())
            }
        }
    }

    fun hit(participant: Participant): Participant {
        return participant.hit(deck.draw())
    }

    companion object {
        const val INIT_CARD_DRAW_COUNT = 2
    }
}
