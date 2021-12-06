package blackjack.domain

class Cards(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {

    val score: Score
        get() = cards.map { it.denomination.score }.reduce { total, score -> total + score }

    fun haveAnAce() = cards.map { it.denomination.name }.contains(ACE)

    fun changeAceToEleven() {
        val ace = cards.first { it.denomination.name == ACE }
        cards.remove(ace)
        cards.add(Card(ace.suit, Denomination.ACE_ELEVEN))
    }

    fun remove(): Card = cards.removeAt(0)

    fun add(card: Card) = cards.add(card)

    fun deepCopy(): Cards = Cards(map(Card::copy).toMutableList())

    companion object {
        private const val ACE = "ACE"
    }
}
