package blackjack

class ListCardDeck(
    private val cards: MutableList<Card>
): CardDeck {

    override fun drawCard(): Card {
        checkCardCount()
        return cards.removeAt(cards.size - 1)
    }

    private fun checkCardCount() {
        if (cards.size == 0) {
            throw IllegalStateException("카드가 없습니다.")
        }
    }

}