package blackjack.card.helper

import domain.card.Card
import domain.card.CardNumber
import domain.card.Cards
import domain.card.Suit
import org.junit.jupiter.params.provider.Arguments

object CardsTestFactory {

    fun makeCards(vararg cards: Card): Cards = Cards(cards = cards.toList())

    fun makeCardsArguments(vararg cards: Card): Arguments = Arguments.of(Cards(cards = cards.toList()))

    fun makeBurstCards(): Cards = makeCards(
        Card(suit = Suit.SPADE, number = CardNumber.JACK),
        Card(suit = Suit.SPADE, number = CardNumber.JACK),
        Card(suit = Suit.SPADE, number = CardNumber.JACK),
    )

    fun makeHitCarts(): Cards = makeCards(
        Card(suit = Suit.SPADE, CardNumber.THREE),
        Card(suit = Suit.SPADE, CardNumber.THREE),
        Card(suit = Suit.SPADE, CardNumber.THREE),
    )

    fun makeStartCards(): Cards = makeCards(
        Card(suit = Suit.SPADE, CardNumber.THREE),
        Card(suit = Suit.SPADE, CardNumber.THREE),
    )

    fun makeStandCards(): Cards = makeCards(
        Card(suit = Suit.SPADE, CardNumber.JACK),
        Card(suit = Suit.SPADE, CardNumber.SEVEN),
    )

    fun makeBlackjackCards(): Cards = makeCards(
        Card(suit = Suit.SPADE, number = CardNumber.JACK),
        Card(suit = Suit.SPADE, number = CardNumber.ACE),
    )
}
