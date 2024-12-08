package blackjack.domain

class Player(
    private var draw: Boolean = true,
    participantName: ParticipantName,
    cards: List<Card> = listOf(),
): Participant(participantName, cards) {
    override fun isDealer(): Boolean = false

    override fun addCard(card: Card): DrawCard {
        cards.add(card)

        if (isBust()) {
            stopDraw()
        }

        return card.toDrawCard()
    }

    private fun isBust(): Boolean = totalCardScore() > BUST_SCORE

    override fun canDraw(): Boolean = draw

    override fun stopDraw() {
        draw = false
    }
}
