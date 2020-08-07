package blackjack.domain

class Deck : DrawStrategy {
    private val cards = Card.ALL.toMutableList()

    @ExperimentalStdlibApi
    override fun fetchCard(): Card {
        return cards.removeFirstOrNull()
            ?: run { reloadAndFetch() }
    }

    @ExperimentalStdlibApi
    private fun reloadAndFetch(): Card {
        this.cards.addAll(Card.ALL)
        return fetchCard()
    }
}
