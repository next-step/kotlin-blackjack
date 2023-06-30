package blackjack.domain

class Deck(randomStrategy: RandomCardStrategy) {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        cards.addAll(randomStrategy.getCards())
    }

    fun getCards(): List<Card> = cards

    fun draw(): Card = cards.removeFirst()
}
