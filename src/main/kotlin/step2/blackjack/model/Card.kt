package step2.blackjack.model


class Card private constructor(val pattern: Pattern, val sign: Sign) {
    companion object {
        fun of(pattern: Pattern, sign: Sign): Card = Card(pattern, sign)
        fun random(): Card = Card(Pattern.random(), Sign.random())
    }
}
