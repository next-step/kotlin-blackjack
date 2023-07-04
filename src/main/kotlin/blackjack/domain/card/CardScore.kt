package blackjack.domain.card

data class CardScore(val primary: Int, val secondary: Int) {
    constructor(value: Int) : this(value, value)

    fun isPrimaryEqualToSecondary() = primary == secondary
}
