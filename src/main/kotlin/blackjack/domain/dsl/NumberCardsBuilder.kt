package blackjack.domain.dsl

import blackjack.domain.NumberCard
import blackjack.domain.SymbolType

@BuilderMarker
class NumberCardsBuilder : Builder<Set<NumberCard>> {
    private val values = mutableSetOf<NumberCard>()

    infix fun IntRange.from(symbolType: SymbolType) =
        this.map { NumberCard(symbol = symbolType, number = it) }.let(values::addAll)

    override fun build(): Set<NumberCard> = values.toSet()
}
