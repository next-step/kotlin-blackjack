package blackjack

data class Player(
    val name: PlayerName,
    val cards: Cards
) {
    override fun toString(): String {
        return "${name.value}카드: $cards"
    }
}