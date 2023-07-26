package blackjack.domain

class Player(val name: String, initCards: List<Card>) {
    val cards: MutableList<Card> = initCards.toMutableList()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun cardSum(): Int {
        val sum = cards.sumOf { it.number.value.toInt() }
        if (cards.map { it.number }.contains(CardNumber.ACE) && sum < 11) {
            return sum + 10
        }
        return sum
    }
}
