package blackjack.domain

data class Player(
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
