package blackjack.domain

data class Card(val shape: String, val number: String) {
    override fun toString(): String {
        return number + shape
    }
}
