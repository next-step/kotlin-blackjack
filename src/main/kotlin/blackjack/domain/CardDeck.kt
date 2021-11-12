package blackjack.domain

import java.util.Stack

data class CardDeck(val cards: Stack<Card>) {
    fun getNextCard(): Card {
        check(cards.isNotEmpty()) { NO_CARD_LEFT_MESSAGE }
        return cards.pop()!!
    }

    companion object {
        private const val NO_CARD_LEFT_MESSAGE = "덱에 카드가 남아있지 않습니다."

        fun getShuffledCards(): Stack<Card> = Stack<Card>()
            .also { it.addAll(getShuffledCardList()) }

        private fun getShuffledCardList() = buildCardPool()
            .values
            .flatten()
            .shuffled()
            .toList()

        private fun buildCardPool(): Map<CardSymbol, List<Card>> = CardSymbol
            .values()
            .associateWith { buildCardsPerSymbol(it) }

        private fun buildCardsPerSymbol(symbol: CardSymbol) = CardNumber
            .values()
            .map { Card(symbol, it) }
            .toList()
    }
}
