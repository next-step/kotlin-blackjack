package blackjack.domain

data class Card(val shape: Shape, val character: Character) {
    fun isAce(): Boolean {
        return character == Character.A
    }
}
