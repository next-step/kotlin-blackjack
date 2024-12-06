package blackjack

class Player(
    val name: PlayerName,
    private val cards: MutableList<Card> = mutableListOf(),
    private var draw: Boolean = true,
) {
    val currentCards: List<DrawCard>
        get() = cards
            .map { it.toDrawCard() }
            .toList()

    fun addCard(card: Card): DrawCard {
        cards.add(card)

        if (totalValue() >= 21) {
            stopDraw()
        }

        return card.toDrawCard()
    }

    fun canDraw(): Boolean = draw

    fun stopDraw() {
        draw = false
    }

    fun totalValue(): Int =
        cards.fold(0) { acc, card -> acc + card.value(acc) }

}

@JvmInline
value class PlayerName(
    val value: String,
)
