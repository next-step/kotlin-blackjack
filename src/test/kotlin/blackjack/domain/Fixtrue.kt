package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.deck.FakeDeckGenerator
import blackjack.view.dto.CreatePlayersDto

fun blackjackFixture(): BlackjackGame {
    val createPlayersDto = listOf(CreatePlayersDto("a", 100), CreatePlayersDto("b", 100))
    val numbers = List(100) { CardNumber.Ten }
    val shapes = listOf(CardShape.Heart)
    return BlackjackGame(createPlayersDto, FakeDeckGenerator(shapes, numbers))
}

fun blackjackCardsFixture(): Cards {
    return Cards(
        listOf(
            Card(shape = CardShape.Heart, number = CardNumber.Ten),
            Card(shape = CardShape.Spade, number = CardNumber.Ace),
        ).toMutableList(),
    )
}

fun bustCardsFixture(): Cards {
    return Cards(
        listOf(
            Card(shape = CardShape.Heart, number = CardNumber.Ten),
            Card(shape = CardShape.Spade, number = CardNumber.Ten),
            Card(shape = CardShape.Diamond, number = CardNumber.Ten),
        ).toMutableList(),
    )
}

fun createCardsFixture(vararg cards: Card): Cards {
    return Cards(cards.toList().toMutableList())
}
