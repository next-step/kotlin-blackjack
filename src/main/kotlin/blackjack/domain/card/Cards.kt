package blackjack.domain.card

import blackjack.domain.player.PlayerStatus

class Cards(_value: List<Card> = emptyList()) {
    private val scoreSet: MutableSet<Int> = mutableSetOf(INIT_SCORE)
    private val value: MutableList<Card> = _value.toMutableList()

    init {
        value.map { updateScoreSet(it) }
    }

    fun getValue(): List<Card> {
        return value.toList()
    }

    fun addCards(cards: Cards) {
        value.addAll(cards.value)
    }

    fun addCard(card: Card) {
        value.add(card)
    }

    fun getOptimizedScore(): Int {
        return scoreSet.max()
    }

    fun updateScoreSet(card: Card) {
        val _scoreSet = setOf(*scoreSet.toTypedArray())

        val newScoreSet = _scoreSet.flatMap { score ->
            if (card.denom.symbol == Denomination.ACE.symbol) {
                Denomination.ACE.getCardScoresValue().map { value -> score + value }
            } else {
                listOf(score + card.denom.cardScores.value.first().value)
            }
        }.distinct().toSet()

        val finalScoreList =
            newScoreSet.filter { it <= PlayerStatus.BLACK_JACK_SCORE }.ifEmpty { setOf(newScoreSet.min()) }

        scoreSet.clear()
        scoreSet.addAll(finalScoreList)
    }

    fun updateScoreSet(cards: Cards) {
        cards.value.forEach { card -> updateScoreSet(card) }
    }

    /**
     * used by CardDeck()
     */
    fun getRandomCard(count: Int): Cards {
        return Cards(value.shuffled().take(count))
    }

    fun getFirstCard(): Card {
        return value.first()
    }

    fun removeAllCards(cards: Cards) {
        value.removeAll(cards.value)
    }

    companion object {
        const val INIT_SCORE = 0
        fun from(vararg card: Card): Cards {
            return Cards(card.toMutableList())
        }
    }
}
