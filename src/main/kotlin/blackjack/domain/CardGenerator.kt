package blackjack.domain

class CardGenerator {
    fun generate(): List<Card> {
        return CardSuit.values()
            .map { suit ->
                CardNumber.values()
                    .map { number ->
                        Card(suit, number)
                    }
            }.flatten()
            .shuffled()
    }
}
