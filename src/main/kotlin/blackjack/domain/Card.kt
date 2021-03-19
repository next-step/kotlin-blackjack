package blackjack.domain

data class Card(val value: Pair<Number, Pattern>) {
    val displayName: String
        get() = value.first.displayName + value.second.pattern
}
