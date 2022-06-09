package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.score.CardScore

abstract class Participant(
    val name: String,
    cards: Cards,
) {
    private val _cards: Cards = cards
    val cardScore get() = CardScore.of(score, cards.size)
    val cards get() = _cards.cards.toList()
    val score get() = _cards.score()

    abstract val isEnd: Boolean

    fun addCard(card: Card) {
        check(!isEnd) {
            "게임이 종료된 이후에는 카드를 추가할 수 없습니다."
        }

        _cards.add(card)
    }
}
