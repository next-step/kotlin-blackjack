package blackjack

class GameCards {
    val cards: Set<Card> = Suit
        .values()
        .flatMap { suit ->
            CardNumber
                .values()
                .map { number ->
                    Card(suit, number)
                }
        }.toSet()
}
