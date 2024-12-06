package blackjack.domain
import kotlin.random.Random

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        Suit.entries.forEach { suit ->
            (2..10).forEach { rank -> cards.add(Card(rank.toString(), suit)) }
            listOf("Ace", "King", "Queen", "Jack").forEach { rank -> cards.add(Card(rank, suit)) }
        }
    }

    fun draw(): Card {
        if (cards.isEmpty()) {
            throw NoSuchElementException("No cards left in the deck")
        }
        return cards.removeAt(Random.nextInt(cards.size))
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
