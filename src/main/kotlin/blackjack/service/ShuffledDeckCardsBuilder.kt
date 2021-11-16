package blackjack.service

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSymbol
import java.util.Stack

class ShuffledDeckCardsBuilder : DeckCardsBuilder {
    override fun build(): Stack<Card> {
        return Stack<Card>()
            .also { it.addAll(getShuffledCardList()) }
    }

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
