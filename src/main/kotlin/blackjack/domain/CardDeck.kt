package blackjack.domain

import java.util.Stack

data class CardDeck(val cards: Stack<Card>) {
    fun getNextCard(): Card {
        check(cards.isNotEmpty()) { NO_CARD_LEFT_MESSAGE }
        return cards.pop()!!
    }

    companion object {
        private const val NO_CARD_LEFT_MESSAGE = "덱에 카드가 남아있지 않습니다."

        private val cardPool: Map<CardSymbol, List<Card>> = buildCardPool()

        fun getShuffledCards(): Stack<Card> {
            return Stack<Card>().also { it.addAll(getShuffledCardList()) }
        }

        private fun getShuffledCardList() = cardPool.values
            .flatten()
            .shuffled()
            .toList()

        private fun buildCardPool(): Map<CardSymbol, List<Card>> {
            return CardSymbol.values().associateWith { buildCardsPerSymbol(it) }
        }

        private fun buildCardsPerSymbol(symbol: CardSymbol) =
            CardNumber.values().map { Card(symbol, it) }.toList()
    }
}
