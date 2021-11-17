package blackjack.domain

import java.util.Stack

data class DealerCardDeck(val cards: Stack<Card>) {
    fun getNextCard(): Card {
        check(cards.isNotEmpty()) { NO_CARD_LEFT_MESSAGE }
        return cards.pop()!!
    }

    companion object {
        private const val NO_CARD_LEFT_MESSAGE = "덱에 카드가 남아있지 않습니다."
    }
}
