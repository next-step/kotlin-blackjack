package nextstep.blackjack

class Player(
    val name: String,
    cards: Set<Card> = emptySet()
) {

    private val nonAceCards: MutableSet<Card> = cards.filter { it.isAce().not() }.toMutableSet()
    private val aceCards: MutableSet<Card> = cards.filter { it.isAce() }.toMutableSet()

    val cards: Set<Card>
        get() = nonAceCards.toSet() + aceCards.toSet()

    fun calculateTotalPoint(): Int {
        val currentPoint: Int = nonAceCards.fold(0) { acc: Int, card: Card -> acc + card.getPoint(acc) }
        if (isEdgeCase(currentPoint)) {
            return currentPoint + aceCards.size
        }
        return aceCards.fold(currentPoint) { acc: Int, aceCard: Card -> acc + aceCard.getPoint(acc) }
    }

    // 특이 케이스 (초반 에이스 두장을 연속으로 받고 10점짜리 카드를 받은 경우) - 원래는 스플릿을 해야하나 스플릿 기능이 없어 엣지 케이스로 분기 처리
    private fun isEdgeCase(currentPoint: Int): Boolean = currentPoint == 10 && aceCards.size > 1

    fun hit(card: Card) {
        when {
            card.isAce() -> aceCards += card
            else -> nonAceCards += card
        }
    }
}
