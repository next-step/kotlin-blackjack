package blackjack.domain

class Cards(
    private val _cards: MutableList<Card>
): MutableList<Card> by _cards {
    val cards: List<Card>
        get() = _cards

    override fun toString(): String {
        return _cards.joinToString(",  ")
    }

    fun addCard(newCard: Card): Cards {
        return from(_cards + newCard)
    }

    fun dec(): Card {
        check(_cards.isNotEmpty()) { "카드가 모두 소진되었습니다." }
        return _cards.removeFirst()
    }

    companion object {
        const val TOTAL_SIZE = 52

        fun from(cardValues: List<Card>? = null): Cards {
            return Cards(cardValues?.toMutableList() ?: mutableListOf())
        }
    }
}
