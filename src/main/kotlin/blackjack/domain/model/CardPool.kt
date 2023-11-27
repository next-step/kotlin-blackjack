package blackjack.domain.model

class CardPool private constructor(private var cards: MutableList<Card>) {
    fun pickAndRemove(): Card {
        check(cards.size > 0) { "카드 풀의 카드가 존재하지 않습니다." }

        val target = (0 until cards.size).random()

        return cards.removeAt(target)
    }

    companion object {
        fun create(): CardPool {
            return CardPool(Cards.create().cards.toMutableList())
        }
    }
}
