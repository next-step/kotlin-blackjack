package blackjack

data class PlayingCard(val type: CardType, val value: CardValue) {
    override fun toString(): String {
        return "${value.value}${type.value}"
    }

    companion object {
        infix fun CardValue.of(type: CardType): PlayingCard = PlayingCard(type, this)
    }
}
