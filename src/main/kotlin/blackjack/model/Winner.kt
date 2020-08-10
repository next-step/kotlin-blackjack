package blackjack.model

object Winner {
    const val WINNING_RANK = 21

    fun calculateRank(decks: List<Deck>): Int {
        val (deckTypesExceptAce, decTypeForAce) = divideDeckType(decks)
        val rank = deckTypesExceptAce
            .map { it.points.first() }
            .reduce { acc, i -> acc + i }

        if (deckTypesExceptAce.size == decks.size) return rank

        return if (rank + decTypeForAce.size * Card.Type.ACE.points.last() <= WINNING_RANK) {
            rank + decTypeForAce.size * Card.Type.ACE.points.last()
        } else {
            rank + decTypeForAce.size * Card.Type.ACE.points.first()
        }
    }

    private fun divideDeckType(decks: List<Deck>): Pair<List<Card.Type>, List<Card.Type>> {
        val deckTypesExceptAceType = decks
            .asSequence()
            .map { it.getDeckType() }
            .filterNot { it.type == Card.Type.ACE.type }
            .toList()
        val aceCountInDeck = decks
            .asSequence()
            .map { it.getDeckType() }
            .filter { it.type == Card.Type.ACE.type }
            .toList()
        return Pair(deckTypesExceptAceType, aceCountInDeck)
    }
}
