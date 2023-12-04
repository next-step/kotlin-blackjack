package blackjack.domain.model

data class Card(val pattern: Pattern, val sign: Sign) {

    companion object {
        fun of(pattern: Pattern, sign: Sign): Card = Card(pattern, sign)
    }
}
