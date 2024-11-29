package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.deck.FakeDeckGenerator
import blackjack.domain.player.Dealer

fun createBlackjackFixture(): BlackjackGame {
    val names = listOf("a", "b")
    val numbers = List(100) { CardNumber.Ten }
    val shapes = listOf(CardShape.Heart)
    return BlackjackGame(Dealer(), names, FakeDeckGenerator(shapes, numbers))
}
