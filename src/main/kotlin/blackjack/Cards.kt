package blackjack

class Cards(
    val items: MutableSet<Card>
) {
    val size: Int
        get() = items.size

    init {
        require(items.size >= MIN_SIZE) { "카드는 최소 2장 이상이어야 합니다." }
    }

    fun add(card: Card) {
        require(!items.contains(card)) { "중복된 카드 ${card}는 추가할 수 없어요." }
        items.add(card)
    }

    companion object {
        private const val MIN_SIZE = 2
    }
}
