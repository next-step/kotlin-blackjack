package blackjack.model

data class Cards(
    private val cards: MutableSet<Card> = mutableSetOf()
) {
    companion object {
        fun init(): Cards {
            return Cards()
        }
    }
}
