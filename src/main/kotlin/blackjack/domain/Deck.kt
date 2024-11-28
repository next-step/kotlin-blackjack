package blackjack.domain

import kotlin.enums.EnumEntries

class Deck {
    val cards: ArrayDeque<Card>

    init {
        cards = initCards()
    }

    private fun initCards(): ArrayDeque<Card> {
        val suits = Suit.entries
        val ranks = Rank.entries
        val cardList = suits.flatMap { suit ->
            initCard(ranks, suit)
        }
        return ArrayDeque(cardList.shuffled())
    }

    private fun initCard(ranks: EnumEntries<Rank>, suit: Suit): List<Card> {
        return ranks.map { rank ->
            Card.of(suit, rank)
        }
    }

    fun drawCard(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalStateException("더 이상 카드가 없습니다.")
    }
}
