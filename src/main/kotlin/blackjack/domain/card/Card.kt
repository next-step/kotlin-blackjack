package blackjack.domain.card

data class Card(val cardShape: CardShape, val cardSymbol: CardSymbol) {

    override fun toString(): String = "%s%s".format(cardSymbol.cardNumber, cardShape.name)
}
