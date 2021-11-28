package blackjack.domain.trump


data class Card(val pattern: Pattern, val number: Number) {

    fun isAce(): Boolean {
        return number === Number.ACE
    }

    companion object {
        val TRUMP_CARDS: List<Card> = Pattern.values()
            .flatMap { pattern -> Number.values().map { Card(pattern, it) } }
            .run { toList() }
    }
}
