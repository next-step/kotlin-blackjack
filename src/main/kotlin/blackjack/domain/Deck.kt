package blackjack.domain

import java.time.LocalDateTime
import kotlin.enums.EnumEntries
import kotlin.random.Random

object Deck {
    var cards = initCards()
    var cardIndex = 0

    private fun initCards(): List<Card> {
        val suits = Suit.entries
        val ranks = Rank.entries
        return suits.flatMap { suit ->
            initCard(ranks, suit)
        }
    }

    private fun initCard(ranks: EnumEntries<Rank>, suit: Suit): List<Card> {
        return ranks.map { rank ->
            Card.of(suit, rank)
        }
    }

    fun shuffle() {
        cardIndex = 0
        cards = cards.shuffled(Random(LocalDateTime.now().nano))
    }

    fun drawCard(): Card {
        return cards[cardIndex++]
    }
}
