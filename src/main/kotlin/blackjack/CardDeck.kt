package blackjack

class CardDeck {
    private val _cards: MutableList<Card> = init()
    val cards: List<Card>
        get() = _cards.deepCopy().shuffled()

    fun pickCard(): Card {
        return _cards.take(1).first().apply {
            _cards.remove(this)
        }
    }

    private fun init(): MutableList<Card> {
        val cards = mutableListOf<Card>()
        Type.values().forEach { type ->
            Value.values().forEach { value ->
                cards.add(Card(type, value))
            }
        }
        return cards.shuffle() as MutableList<Card>
    }

    private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }
}
