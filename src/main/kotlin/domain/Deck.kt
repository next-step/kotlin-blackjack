package domain

class Deck {

    init {
        val defaultDeck = Suit.values().map { suit ->
            Denomination.values().map { denomination ->
                Card(Pair(suit, denomination))
            }
        }
    }
}
