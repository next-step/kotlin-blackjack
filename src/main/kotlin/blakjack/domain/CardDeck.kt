package blakjack.domain

private typealias Used = Boolean

class CardDeck(
    cards: Cards = Cards.all()
) {
    private val cards: CardUsedMap = CardUsedMap(cards.values.associateWith { false }.toMutableMap())
    private var countOfUsed = 0

    fun getCardRandomly(): Card {
        require(hasUnusedCards()) { "더 이상 사용할 수 있는 카드가 없습니다." }

        countOfUsed++
        return cards.getUnusedCards().getRandomCard().also { cards[it] = true }
    }

    private fun hasUnusedCards(): Boolean {
        return countOfUsed < cards.size
    }

    private fun Cards.getRandomCard(): Card {
        return values.shuffled().first()
    }

    companion object {
        fun create(): CardDeck {
            return CardDeck()
        }
    }
}

private class CardUsedMap(cards: MutableMap<Card, Used>) : MutableMap<Card, Used> by cards {
    fun getUnusedCards(): Cards {
        return Cards(filter { !it.value }.keys)
    }
}
