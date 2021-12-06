package blackjack.domain

data class Cards(private val _cards: MutableList<Card>) {

    val cards: List<Card>
        get() = _cards.map { it.copy() }

    fun getScore(): Score {
        var sum = _cards
            .filter { it.denomination != Denomination.ACE }
            .sumOf { it.denomination.score.score }
            .let { Score.from(it) }

        repeat(countAce()) {
            sum += Score.getAceScore(sum)
        }
        return sum
    }

    private fun countAce(): Int {
        return _cards.count(Card::isAce)
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    companion object {
        fun from(cardList: List<Card> = listOf()): Cards {
            return Cards(cardList.toMutableList())
        }
    }
}
