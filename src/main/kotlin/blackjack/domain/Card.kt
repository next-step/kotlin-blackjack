package blackjack.domain

data class Card(val symbol: CardSymbol, val number: CardNumber) {
    override fun toString(): String {
        return number.text + symbol.koreanName
    }

    fun hasAce(): Boolean {
        return CardNumber.isAce(number.text)
    }
}
