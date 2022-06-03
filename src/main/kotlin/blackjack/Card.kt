package blackjack

interface Card {
    val symbol: Symbol
    fun score(): Int
}

data class NumberCard(override val symbol: Symbol, val number: Int) : Card {
    init {
        require(number in 2..10) { "숫자 카드의 범위는 2 ~ 10 입니다." }
    }

    override fun score(): Int = number
}

data class AlphabetCard(override val symbol: Symbol, val alphabet: Char) : Card {
    init {
        require(alphabet in listOf('A', 'J', 'K', 'Q')) { "알파벳 카드는 A,J,K,Q 만 가능합니다." }
    }

    override fun score(): Int {
        TODO("Not yet implemented")
    }
}
