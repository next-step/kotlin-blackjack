package blackjack.domain

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
        } // 딜러와 플레이어가 달라지는 부분

        return card.toDrawCard()
    }

    private fun isBust(): Boolean = totalValue() > 21

    fun totalValue(): Int =
        cards
            .sortedBy { sortForAceAfter(it) }
            .fold(0) { acc, card -> acc + card.value(acc) }

    private fun sortForAceAfter(card: Card): Int =
        if (card.isAce()) {
            1
        } else {
            0
        }

    fun canDraw(): Boolean = draw // 딜러와 플레이어가 달라지는 부분

    fun stopDraw() {
        draw = false // 딜러와 플레이어가 달라지는 부분
    }
}

@JvmInline
value class PlayerName(
    val value: String,
)
