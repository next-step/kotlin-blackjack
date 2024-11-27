class Player(
    val name: String,
    private val hands: Hands,
)

class Hands(
    private val cards: MutableList<Card>,
) {
    fun totalValue(): Int =
        cards.fold(0) { acc, card -> acc + card.value(acc) }
}
