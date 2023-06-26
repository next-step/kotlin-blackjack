package blackjack.domain

class Player(
    val name: String,
    val cards: MutableList<Card> = mutableListOf()
) {

    fun hit(card: Card) {
        cards.add(card)
    }

    fun getTotalScore(): Int {
        var sum = 0
        cards.forEach { card ->
            sum += Denomination.from(card.denomination, sum)
        }
        return sum
    }
}
