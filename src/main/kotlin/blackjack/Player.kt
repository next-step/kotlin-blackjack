package blackjack

class Player(
    val name: PlayerName,
    private var draw: Boolean = true,
    cards: List<Card> = listOf(),
) {
    private val cards: MutableList<Card> = cards.toMutableList()

    val currentCards: List<DrawCard>
        get() = cards
            .map { it.toDrawCard() }
            .toList()

    fun addCard(card: Card): DrawCard {
        cards.add(card)

        if (isBust()) {
            stopDraw()
        }

        return card.toDrawCard()
    }

    private fun isBust(): Boolean = totalValue() > 21

    fun totalValue(): Int =
        cards.fold(0) { acc, card -> acc + card.value(acc) }

    fun canDraw(): Boolean = draw

    fun stopDraw() {
        draw = false
    }
}

@JvmInline
value class PlayerName(
    val value: String,
)
