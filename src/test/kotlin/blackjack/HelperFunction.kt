package blackjack

import blackjack.domain.Card
import blackjack.domain.CardMark
import blackjack.domain.CardNumber
import blackjack.domain.Deck

fun createAceCard(): Card {
    return Card(CardNumber.ACE, CardMark.HEART)
}

fun createBasicCard(
    number: CardNumber,
    mark: CardMark,
): Card {
    return Card(number, mark)
}

fun createPlayers(number: Int): List<String> {
    return (1..number).map { "name$it" }
}

private val CACHED_CARDS =
    mutableListOf(
        Card(CardNumber.ACE, CardMark.HEART),
        Card(CardNumber.TEN, CardMark.HEART),
        Card(CardNumber.ACE, CardMark.HEART),
        Card(CardNumber.TEN, CardMark.HEART),
        Card(CardNumber.ACE, CardMark.HEART),
    )

fun fixedDeck() = Deck { CACHED_CARDS.removeLast() }
