package blackjack.domain

import blackjack.view.askAceIsEleven

class Cards(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {

    val score: Score
        get() {
            if (!haveAnACE()) {
                return calculateScore()
            }

            if (askAceIsEleven()) {
                val ace = cards.filter { it.denomination.name == ACE }.first()
                cards.remove(ace)
                cards.add(Card(ace.suit, Denomination.ACE_ELEVEN))
                return score
            }

            return calculateScore()
        }

    private fun calculateScore() = cards.map { it.denomination.score }
        .reduce { total, score -> total + score }

    private fun haveAnACE() = cards.map { it.denomination.name }.contains(ACE)

    fun remove(): Card = cards.removeAt(0)

    fun add(card: Card) = cards.add(card)

    fun deepCopy(): Cards = Cards(map(Card::copy).toMutableList())

    override fun toString(): String = "${cards.joinToString(transform = Card::toString)}"

    companion object {
        private const val ACE = "ACE"
    }
}
