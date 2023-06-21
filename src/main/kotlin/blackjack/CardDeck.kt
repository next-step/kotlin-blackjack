package blackjack

class CardDeck(var deck: List<Card> = cardDeck, var usedDeck: List<Card> = emptyList()) {
    companion object {
        val cardDeck = CardType.values().flatMap { type -> Denomination.values().map { denom -> Card(denom, type) } }
    }
}
