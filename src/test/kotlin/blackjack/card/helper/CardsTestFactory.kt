package blackjack.card.helper

import domain.card.Card
import domain.card.Cards
import org.junit.jupiter.params.provider.Arguments

object CardsTestFactory {

    fun makeCards(vararg cards: Card): Cards = Cards(cards = cards.toList())

    fun makeCardsArguments(vararg cards: Card): Arguments = Arguments.of(Cards(cards = cards.toList()))
}
