package blackjack

data class JackCard(override val symbol: Symbol) : Card {
    override fun score(): Score = SingleScore(10)
}

data class QueenCard(override val symbol: Symbol) : Card {
    override fun score(): Score = SingleScore(10)
}

data class KingCard(override val symbol: Symbol) : Card {
    override fun score(): Score = SingleScore(10)
}

data class AceCard(override val symbol: Symbol) : Card {
    override fun score(): Score = SelectableScore(listOf(SingleScore(1), SingleScore(11)))
}

fun AlphabetCard(symbol: Symbol, alphabet: Char): Card {
    return when (alphabet) {
        'A' -> AceCard(symbol)
        'J' -> JackCard(symbol)
        'Q' -> QueenCard(symbol)
        'K' -> KingCard(symbol)
        else -> throw IllegalArgumentException("알파벳 카드는 A,J,K,Q 만 가능합니다.")
    }
}
