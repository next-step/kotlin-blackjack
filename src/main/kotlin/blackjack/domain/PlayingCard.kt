package blackjack.domain

data class PlayingCard(val type: CardType, val value: CardValue) {
    override fun toString(): String {
        return "${value.displayName}${type.displayName}"
    }

    companion object {
        infix fun CardValue.of(type: CardType): PlayingCard = PlayingCard(type, this)
    }
}
