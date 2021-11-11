package blackjack.domain

data class Card(val symbol: CardSymbol, val number: CardNumber) {
    override fun toString(): String {
정        return number.rank + symbol.koreanName
    }

    fun hasAce(): Boolean {
        return CardNumber.isAce(number.rank)
    }
}
