package domains

class Deck(
    private val pokerNumbers: List<PokerNumber>,
    private val pokerShapes: List<PokerShape>,
) {
    val deck: MutableList<Card> = mutableListOf()

    init {
        createNewDeck()
    }

    fun drawCard(): Card {
        val pickedCard = deck.random()
        deck.remove(pickedCard)
        return pickedCard
    }

    private fun createNewDeck() {
        pokerNumbers.forEach { number ->
            pokerShapes.forEach { shape ->
                deck.add(Card(number, shape))
            }
        }
    }
}
