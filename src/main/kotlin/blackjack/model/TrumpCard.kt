package blackjack.model

data class TrumpCard(val shape: TrumpCardShape, val number: TrumpCardNumber)

typealias CardDeck = Collection<TrumpCard>
