package blackjack

data class JackCard(override val symbol: Symbol) : Card {
    override fun score(): Int {
        TODO("Not yet implemented")
    }
}

data class QueenCard(override val symbol: Symbol) : Card {
    override fun score(): Int {
        TODO("Not yet implemented")
    }
}

data class KingCard(override val symbol: Symbol) : Card {
    override fun score(): Int {
        TODO("Not yet implemented")
    }
}

data class AceCard(override val symbol: Symbol) : Card {
    override fun score(): Int {
        TODO("Not yet implemented")
    }
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
