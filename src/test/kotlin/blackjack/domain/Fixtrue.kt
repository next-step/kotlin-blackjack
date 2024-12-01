package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.deck.FakeDeckGenerator
import blackjack.view.dto.CreatePlayersDto

fun createBlackjackFixture(): BlackjackGame {
    val createPlayersDto = listOf(CreatePlayersDto("a", 100), CreatePlayersDto("b", 100))
    val numbers = List(100) { CardNumber.Ten }
    val shapes = listOf(CardShape.Heart)
    return BlackjackGame(createPlayersDto, FakeDeckGenerator(shapes, numbers))
}
