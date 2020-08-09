package blackjack.domain

class Deck : DrawStrategy {
    private val cards = mutableListOf<Card>()

    init {
        cards.addAll(Card.ALL.shuffled())
    }

    @ExperimentalStdlibApi
    override fun fetchCard(): Card {
        return cards.removeFirstOrNull()
            ?: run { reloadAndFetch() }
    }

    @ExperimentalStdlibApi
    private fun reloadAndFetch(): Card {
        this.cards.addAll(Card.ALL.shuffled())
        return fetchCard()
    }
}
