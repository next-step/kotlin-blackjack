package blackjack

data class Card(val rank: Rank, val suit: Suit) {
    fun possibleSums(currentSums: List<Int>): List<Int> {
        return currentSums.flatMap { sum ->
            rank.possibleValues().map { value ->
                sum + value
            }
        }
    }

    fun display(): String {
        return "${rank.symbol}${suit.symbol}"
    }

    companion object {
        val ALL_CARDS =
            Rank.entries.flatMap { rank ->
                Suit.entries.map { suit ->
                    Card(rank, suit)
                }
            }
    }
}
