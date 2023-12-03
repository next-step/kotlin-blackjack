package blackjack.domain

class Participant (
    val name: String,
    cards: List<Card>,
) {
    private val _cards: MutableList<Card> = cards.listCopy().toMutableList()
    val cards: List<Card>
        get() = _cards.listCopy()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getScore(): Int {
        val (aces, cardList) = cards.partition { it.number == CardNumber.ACE }
        var score = cardList.sumOf { it.number.score }
        aces.forEach { score += it.number.getAceNumber(score) }
        return score
    }

    private fun List<Card>.listCopy(): List<Card> {
        return this.map { it.copy() }
    }
}
