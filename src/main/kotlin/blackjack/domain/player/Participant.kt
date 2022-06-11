package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Participant(
    val name: String,
    val cards: Cards,
) {
    abstract fun isEnd(): Boolean

    fun addCard(card: Card) {
        check(!isEnd()) {
            "게임이 종료된 이후에는 카드를 추가할 수 없습니다."
        }

        cards.add(card)
    }
}
