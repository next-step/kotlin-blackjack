package blackJack.domain

class Deck() {
    var cards = DEFAULT_DECK
        private set

    companion object {
        val DEFAULT_DECK = Shape.values().flatMap { shape -> Denomination.values().map { Card(shape, it) } }
    }
}
