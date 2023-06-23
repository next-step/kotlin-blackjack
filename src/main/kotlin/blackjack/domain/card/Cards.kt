package blackjack.domain.card

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus

class Cards(
    val value: MutableList<Card> = mutableListOf(),
    private val scoreSet: MutableSet<Int> = mutableSetOf(INIT_SCORE),
) {

    init {
        value.map { updateScoreSet(it) }
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

    companion object {
        const val INIT_SCORE = 0
        fun from(vararg card: Card): Cards {
            return Cards(card.toMutableList())
        }
    }
}
