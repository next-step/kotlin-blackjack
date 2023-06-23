package next.step.blackjack.domain

object Casino {
    val cards: List<Card> = shuffled(6)

    private fun shuffled(deckCnt: Int): List<Card> = (1..deckCnt).flatMap { deck() }.shuffled()

    private fun deck(): List<Card> = CardFace.values().flatMap { mapSymbols(it) }

    private fun mapSymbols(face: CardFace) = CardSymbol.values().map { Card.of(face, it) }
}