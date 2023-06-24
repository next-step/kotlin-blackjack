package blakjack.domain

private typealias Used = Boolean

class CardDeck {
    private val cards: CardUsedMap = CardUsedMap(Card.values().associateWith { false }.toMutableMap())
    private var countOfUsed = 0

    fun getCardRandomly(): Card {
        require(!isAllUsed()) { "더 이상 사용할 수 있는 카드가 없습니다." }

        countOfUsed++
        return cards.getUnusedCards().getRandomCard().also { cards[it] = true }
    }

    private fun isAllUsed(): Boolean {
        return countOfUsed == cards.size
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
