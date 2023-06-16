package blackjack.domain.card

data class MockPlayingCards(private val cards: MutableSet<Card>) : PlayingCards(cards = cards.toMutableSet())
