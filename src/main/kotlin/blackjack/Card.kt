package blackjack

interface Card {
    val symbol: Symbol
    fun score(): Int
}

data class NumberCard(override val symbol: Symbol, val number: Int) : Card {
    override fun score(): Int = number
}

data class AlphabetCard(override val symbol: Symbol, val alphabet: Char) : Card {
    override fun score(): Int {
        TODO("Not yet implemented")
    }
}
