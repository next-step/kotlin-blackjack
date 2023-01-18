package blackjack.domains.deck

class Deck(
    private val pokerNumbers: List<PokerNumber>,
    private val pokerShapes: List<PokerShape>,
) {
    private val _deck: MutableList<Card> = mutableListOf()

    val deck: List<Card>
        get() = _deck.toList()

    init {
        createNewDeck()
    }

    fun drawCard(): Card {
        val pickedCard = _deck.random()
        _deck.remove(pickedCard)
        return pickedCard
    }

    private fun createNewDeck() {
        pokerNumbers.forEach { number ->
            pokerShapes.forEach { shape ->
                _deck.add(Card(number, shape))
            }
        }
    }
}
