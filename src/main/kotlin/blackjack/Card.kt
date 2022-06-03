package blackjack

interface Card {
    val symbol: Symbol
}

data class NumberCard(override val symbol: Symbol, val number: Int) : Card
data class AlphabetCard(override val symbol: Symbol, val alphabet: Char) : Card
