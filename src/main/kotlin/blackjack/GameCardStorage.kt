package blackjack

class GameCardStorage(
    val cards: Set<Card>,
) {
    init {
        require(cards.size == 52) { "게임에서 사용될 카드들은 조커를 제외한 모든 트럼프 카드 52장이어야 합니다." }
    }
}
