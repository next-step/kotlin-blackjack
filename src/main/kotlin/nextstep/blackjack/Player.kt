package nextstep.blackjack

class Player(cards: Set<Card>) {

    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun calculateTotalPoint(): Int = _cards.fold(0) { acc: Int, card: Card -> acc + card.getPoint(acc) }
}
