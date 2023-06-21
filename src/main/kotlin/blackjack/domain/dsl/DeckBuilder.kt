package blackjack.domain.dsl

import blackjack.domain.AceCard
import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.SymbolType

@BuilderMarker
class DeckBuilder : Builder<Deck> {
    private val cards: MutableSet<Card> = mutableSetOf()

    fun numberCards(block: NumberCardsBuilder.() -> Unit) = NumberCardsBuilder().apply(block).build().let(cards::addAll)

    fun faceCards(block: FaceCardsBuilder.() -> Unit) = FaceCardsBuilder().apply(block).build().let(cards::addAll)

    fun aceCards(vararg symbols: SymbolType) = symbols.map(::AceCard).let(cards::addAll)

    override fun build(): Deck = Deck(values = cards)
}


fun buildDeck(block: DeckBuilder.() -> Unit): Deck = DeckBuilder().apply(block).build()
