package blackjack.domain.card

data class Card(val number: CardNumber, val shape: CardShape) {
    fun numberName() = number.displayName
    fun shapeName() = shape.displayName
}
