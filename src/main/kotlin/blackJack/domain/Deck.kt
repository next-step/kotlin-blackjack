package blackJack.domain

class Deck() {
    private val shape = listOf("♠", "♣", "♥", "♦")
    private val _cards = mutableListOf<Card>()
    val cards = makeDeck()

    private fun makeDeck(): List<Card> {
        shape.forEach { makeCard(it) }
        return _cards
    }

    private fun makeCard(shape: String) {
        (FIRST..LAST).map { _cards.add(makeCard(shape, it)) }
    }

    private fun makeCard(shape: String, number: Int): Card {
        return when (number) {
            1 -> (Card("${shape}A", number))

            in 2..10 -> (Card("${shape}${number}", number))

            11 -> (Card("${shape}J", 10))

            12 -> (Card("${shape}Q", 10))

            13 -> (Card("${shape}K", 10))

            else -> throw IllegalArgumentException("해당 번호의 카드는 존재하지 않습니다.")
        }
    }

    companion object {
        private const val FIRST = 1
        private const val LAST = 13
    }
}
