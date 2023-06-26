package blackjack.domain

data class Card(val cardShape: CardShape, val cardSymbol: CardSymbol) {
    fun show(): String = "%s%s".format(cardSymbol.cardNumber, cardShape.name)
}
