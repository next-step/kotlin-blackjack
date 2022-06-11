package blackjack.domain.card

class CardDrawer(private val cards: Cards) {
    private var index = 0

    fun draw(): Card? = kotlin.runCatching { cards[index++] }.getOrNull()
}
