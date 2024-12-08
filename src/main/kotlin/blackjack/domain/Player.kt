package blackjack.domain

class Player(
    private var draw: Boolean = true,
    playerName: PlayerName,
    cards: List<Card> = listOf(),
): Participant(playerName, cards) {
    override fun addCard(card: Card): DrawCard {
        cards.add(card)

        if (isBust()) {
            stopDraw()
        }

        return card.toDrawCard()
    }

    private fun isBust(): Boolean = totalCardScore() > 21

    override fun canDraw(): Boolean = draw

    override fun stopDraw() {
        draw = false
    }
}

@JvmInline
value class PlayerName(
    val value: String,
)
