package blackjack.util

import blackjack.domain.card.Card
import blackjack.domain.card.RandomCardDeck.Companion.ACE
import blackjack.domain.card.RandomCardDeck.Companion.DIAMOND
import blackjack.domain.card.RandomCardDeck.Companion.JACK
import blackjack.domain.card.RandomCardDeck.Companion.KING

object Cards {
    fun createCards(): MutableList<Card> {
        return mutableListOf(
            Card(DIAMOND, "2"),
            Card(DIAMOND, "3"),
            Card(DIAMOND, "4"),
            Card(DIAMOND, "5"),
        )
    }

    fun createCardsOver21(): MutableList<Card> {
        return mutableListOf(
            Card(DIAMOND, ACE),
            Card(DIAMOND, JACK),
            Card(DIAMOND, KING),
            Card(DIAMOND, "5"),
            Card(DIAMOND, KING),
        )
    }
}
