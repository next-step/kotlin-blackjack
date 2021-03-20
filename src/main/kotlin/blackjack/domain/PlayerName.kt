package blackjack.domain

data class PlayerName(
    val name: String
) {

    override fun toString(): String {
        return "$name"
    }
}