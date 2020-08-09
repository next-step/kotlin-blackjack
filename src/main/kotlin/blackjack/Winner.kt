package blackjack

object Winner {
    const val WINNING_RANK = 21

    fun calculateRank(decks: List<Deck>): Int {
        val deckTypesExceptAceType = decks.map { it.getDeckType() }
            .filterNot { it.nickName == Card.Type.ACE.nickName }
        val aceCountInDeck = decks.map { it.getDeckType() }.filter { it.nickName == Card.Type.ACE.nickName }.size
        val rank = deckTypesExceptAceType.map { it.points.first() }.reduce { acc, i -> acc + i }

        if (deckTypesExceptAceType.size == decks.size) return rank

        return if (rank + aceCountInDeck * Card.Type.ACE.points.last() <= WINNING_RANK) {
            rank + aceCountInDeck * Card.Type.ACE.points.last()
        } else {
            rank + aceCountInDeck * Card.Type.ACE.points.first()
        }
    }
}
