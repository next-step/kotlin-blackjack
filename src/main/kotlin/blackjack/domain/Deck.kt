package blackjack.domain
import kotlin.random.Random

class Deck(private val random: Random = Random.Default) {
    private val cards = mutableListOf<Card>()

    init {
        Suit.entries.forEach { suit ->
            Rank.entries.forEach { rank ->
                cards.add(Card(rank, suit))
            }
        }
    }

    fun draw(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("No cards left in the deck")
        }
        return cards.removeAt(random.nextInt(cards.size))
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
