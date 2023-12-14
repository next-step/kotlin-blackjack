package game.blackjack.domain

data class Card(val number: CardNumber, val shape: CardShape) {
    override fun toString(): String {
        return "${number.value}${shape.value}"
    }
}
