package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.deck.FakeDeckGenerator

fun createBlackjackFixture(): BlackjackGame {
    val names = listOf("a", "b")
    val numbers = List(100) { CardNumber.Ten }
    val shapes = listOf(CardShape.Heart)
    return BlackjackGame(names, FakeDeckGenerator(shapes, numbers))
}
