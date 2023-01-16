package model

class Deck {
    companion object {
        private val cards = CardNumber.values().flatMap { num ->
            CardShape.values().map { shape ->
                Card(num, shape)
            }
        }.shuffled().toMutableList()

        fun selectCard(): Card {
            return cards.removeAt(0)
        }
    }
}
