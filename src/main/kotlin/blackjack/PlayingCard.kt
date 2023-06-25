package blackjack

data class PlayingCard(val type: CardType, val value: CardValue) {
    override fun toString(): String {
        return "${type.value}${value.value}"
    }
}
