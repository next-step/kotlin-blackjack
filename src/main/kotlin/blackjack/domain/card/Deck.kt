package blackjack.domain.card

class Deck {

    private val numbers = CardNumber.values()
    private val deck = builder()

    fun draw(): Card {
        return deck.removeFirst()
    }

    private fun builder(): MutableList<Card> {
        val cards = mutableListOf<Card>()
        cards.addAll(spades())
        cards.addAll(heart())
        cards.addAll(clover())
        cards.addAll(diamond())
        cards.shuffle()
        return cards
    }

    private fun spades(): List<Card> {
        return numbers.map { Card(it, CardType.SPADES) }
    }

    private fun heart(): List<Card> {
        return numbers.map { Card(it, CardType.HEART) }
    }

    private fun clover(): List<Card> {
        return numbers.map { Card(it, CardType.CLOVER) }
    }

    private fun diamond(): List<Card> {
        return numbers.map { Card(it, CardType.DIAMOND) }
    }
}
