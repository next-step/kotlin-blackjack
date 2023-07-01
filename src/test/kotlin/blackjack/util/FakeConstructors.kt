package blackjack.util

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Suit
import blackjack.domain.user.Dealer
import blackjack.domain.user.User
import blackjack.domain.user.UserDrawInterface

fun Dealer(cardPairs: List<Pair<Suit, CardNumber>>): Dealer {
    val cards = cardPairToCard(cardPairs)
    return Dealer(cards)
}

fun User(
    name: String = "홍길동",
    cardPairs: List<Pair<Suit, CardNumber>>,
    drawInterface: UserDrawInterface = TEST_USER_DRAW_INTERFACE,
): User {
    val cards = cardPairToCard(cardPairs)
    return User(name, cards, drawInterface)
}

fun cardPairToCard(cardPairs: List<Pair<Suit, CardNumber>>): Cards {
    return Cards(List(cardPairs.size) { Card(cardPairs[it].first, cardPairs[it].second) })
}
