package blackjack.domain

class Deck(
    cards: Set<Card> = CARD_LIST
) {
    private val _cards = cards.toMutableSet()
    val cards: Set<Card> get() = _cards.toSet()

    private val totalCardCount = TotalCard().count

    init {
        require(cards.size == totalCardCount) {
            "카드는 ${totalCardCount}으로 구성되어야 합니다."
        }
    }

    fun draw(): Card {
        require(cards.isNotEmpty()) {
            "사용할 카드가 없습니다."
        }

        return cards.first().apply {
            _cards.remove(this)
        }
    }

    companion object {
        val CARD_LIST: Set<Card> = Suit.values().map { character ->
            Denomination.values().map { number ->
                Card(suit = character, denomination = number)
            }
        }.flatten().shuffled().toSet()
    }
}
