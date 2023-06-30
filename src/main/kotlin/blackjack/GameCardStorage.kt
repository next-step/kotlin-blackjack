package blackjack

class GameCardStorage(
    cardList: Set<Card>,
) {
    private val cards = cardList.toMutableList()

    val currentCards: List<Card>
        get() = cards.toList()

    init {
        require(cards.size == 52) { "게임에서 사용될 카드들은 조커를 제외한 모든 트럼프 카드 52장이어야 합니다." }
    }

    fun drawCard(): Card {
        return cards.removeFirst()
    }
}
