package blackjack.domain

class Player(val name: String) {
    val cards: MutableList<Card> = mutableListOf()
    private val totalWithoutAce
        get() = cards.filter { it.denomination != Denomination.ACE }.sumBy { it.denomination.calculate() }
    val total
        get() = cards.filter { it.denomination == Denomination.ACE }
            .fold(totalWithoutAce) { total, ace -> total + ace.denomination.calculate(total) }
    val isBust get() = total > 21
    var isStay: Boolean = false

    fun addCard(card: Card): List<Card> {
        cards.add(card)
        return cards
    }

    fun addCards(card: List<Card>): List<Card> {
        cards.addAll(card)
        return cards
    }

    fun hit(): Boolean {
        return if (isBust) false
        else {
            addCard(Deck.take())
            true
        }
    }

    fun stay(): Boolean {
        isStay = true
        return isStay
    }

    fun isGaming(): Boolean {
        return !isBust && !isStay
    }

    fun choose(yOrN: String): Boolean {
        return if (yOrN == "y") {
            hit()
        } else false
    }
}
