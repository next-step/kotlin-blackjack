package blackjack.infrastructure

import blackjack.domain.Card
import blackjack.domain.CardMark
import blackjack.domain.CardNumber
import blackjack.domain.Deck

class RandomDeck : Deck {
    override fun draw(): Card {
        CACHED_CARDS.shuffle()
        check(CACHED_CARDS.isNotEmpty()) { "Deck이 비었습니다." }
        return CACHED_CARDS.removeLast()
    }

    companion object {
        private val CACHED_CARDS =
            CardNumber.entries.flatMap { number ->
                CardMark.entries.map { mark -> Card(number, mark) }
            }.toMutableList()
    }
}
