package blackjack.domain.dsl

import blackjack.domain.AceCard
import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.NumberCard
import blackjack.domain.SymbolType

@BuilderMarker
class DeckBuilder : Builder<Deck> {
    private val cards: MutableSet<Card> = mutableSetOf()
    private val symbols: MutableSet<SymbolType> = mutableSetOf()

    fun symbols(vararg values: SymbolType) {
        symbols.addAll(values)
    }

    fun numbers(range: IntRange) {
        symbols.forEach { numbers(symbol = it, range = range) }
    }

    fun numbers(symbol: SymbolType, range: IntRange) = range.forEach {
        cards.add(NumberCard(symbol = symbol, number = it))
    }

    fun ace(symbol: SymbolType? = null) {
        if (symbol != null) {
            cards.add(AceCard(symbol = symbol))
        } else {
            symbols.forEach(::ace)
        }
    }

    override fun build(): Deck {
        TODO("Not yet implemented")
    }
}

fun buildDeck(block: DeckBuilder.() -> Unit): Deck = DeckBuilder().apply(block).build()
