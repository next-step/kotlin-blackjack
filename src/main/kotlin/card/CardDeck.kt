package card

object CardDeck {
    val cards: List<PlayingCard> = CardRank.values().flatMap { rank ->
        Suit.values().map { suit ->
            PlayingCard(suit = suit, cardRank = rank)
        }
    }
}
