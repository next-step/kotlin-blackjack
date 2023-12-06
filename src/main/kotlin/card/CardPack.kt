package card

object CardPack {
    val cards: List<PlayingCard> = CardRank.values().flatMap { rank ->
        Suit.values().map { suit ->
            PlayingCard.of(suit = suit, cardRank = rank)
        }
    }
}
