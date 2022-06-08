package blackjack.domain.card

data class Card(val pattern: String, val number: String) {
    override fun toString(): String {
        return number + pattern
    }
}
