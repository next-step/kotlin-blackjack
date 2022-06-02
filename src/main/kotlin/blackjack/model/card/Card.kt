package blackjack.model.card

data class Card(val denomination: Denomination, val shape: CardShape) {
    val displayName: String
        get() = "${denomination.displayName}${shape.displayName}"
}
