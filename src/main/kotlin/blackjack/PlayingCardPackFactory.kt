package blackjack

object PlayingCardPackFactory {

    private val DEFAULT_CARDS: List<PlayingCard> =
        (CardType.values() * CardValue.values())
            .map { (type, value) -> PlayingCard(type, value) }

    fun createPack(): PlayingCardPack {
        return PlayingCardPack(DEFAULT_CARDS)
    }

    private infix operator fun <T, U> Array<T>.times(other: Array<U>): List<Pair<T, U>> {
        return this.flatMap { t -> other.map { u -> t to u } }
    }
}
