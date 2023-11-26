package blackjack.domain

class CardBundle private constructor(private val bundle: MutableList<Card>) {
    init {
        val fullStackSize = CardSuitInfo.values().size * CardNumberInfo.values().size
        require(bundle.size == fullStackSize) { "카드 1 벌은 ${fullStackSize}장이어야 합니다." }
    }

    fun draw(): Card {
        check(bundle.isNotEmpty()) { "카드가 부족합니다." }

        return bundle.removeAt((0..bundle.lastIndex).random())
    }

    companion object {
        fun getBundle(): CardBundle {
            val cards = mutableListOf<Card>()
            CardSuitInfo.values().map { suit ->
                CardNumberInfo.values().map { cards.add(Card(suit, it)) }
            }

            return CardBundle(cards)
        }
    }
}
