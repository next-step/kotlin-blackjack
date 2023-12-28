package game.blackjack.v2.domain

import game.blackjack.v2.domain.Blackjack.INITIAL_DRAW_COUNT
import game.blackjack.v2.domain.card.Card
import game.blackjack.v2.domain.card.CardNumber
import game.blackjack.v2.domain.card.CardShape

object Deck {
    private val allCards: List<Card> = CardShape.values()
        .flatMap { shape -> CardNumber.values().map { Card(it, shape) } }
    private val cards: ArrayDeque<Card> = ArrayDeque(allCards.shuffled())
    val size: Int get() = cards.size
    
    fun initialDraw() = List(INITIAL_DRAW_COUNT) { drawOnce() }

    fun drawOnce() = cards.removeFirstOrNull()
        ?: throw IllegalStateException("게임 덱의 카드를 모두 소진했습니다.")
}
