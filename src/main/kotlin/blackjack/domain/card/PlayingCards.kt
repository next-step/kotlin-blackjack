package blackjack.domain.card

import blackjack.domain.deck.Denomination
import blackjack.domain.game.BlackjackGame

class PlayingCards(private val cards: MutableSet<Card> = mutableSetOf()) : Set<Card> by cards {

    fun add(card: Card) {
        check(value = card !in cards) {
            "중복된 카드를 추가할 수 없습니다. 카드 : $card"
        }

        cards.add(element = card)
    }

    fun includeDenomination(denomination: Denomination): Boolean = cards.any { it.denomination == denomination }

    fun sumScore(): Int = cards.sumOf { it.denomination.score }

    fun isBust(): Boolean = sumScore() > BlackjackGame.BUST_SCORE

    fun isBlackjack(): Boolean = sumScore() == BlackjackGame.BUST_SCORE && cards.size == BlackjackGame.INIT_HAND_COUNT
}
