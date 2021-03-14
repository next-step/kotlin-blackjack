package blackjack.domain

class Cards(vararg elements: Card) {
    private val elements = elements.toMutableSet()

    init {
        require(this.elements.size == 2)
    }
}
