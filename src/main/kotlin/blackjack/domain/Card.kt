package blackjack.domain

data class Card(val number: CardNumber, val shape: CardShape) {
    override fun toString(): String = number.value + shape.label
}
