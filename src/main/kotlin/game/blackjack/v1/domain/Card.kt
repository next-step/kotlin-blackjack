package game.blackjack.v1.domain

data class Card(val number: CardNumber, val shape: CardShape) {
    override fun toString() = "${number.value}${shape.value}"
}
