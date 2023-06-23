package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination

class Player(
    val name: Name = Name(),
    val scoreSet: MutableSet<Int> = mutableSetOf(INIT_SCORE),
) {
    val cards: Cards = Cards()
    private var status: PlayerStatus = PlayerStatus.RECEIVE

    init {
        cards.value.map { updateScoreSet(it) }
        updateStatus()
    }

    fun isReceivable(): Boolean {
        return status == PlayerStatus.RECEIVE || status == PlayerStatus.BLACK_JACK
    }

    fun isBlackJack(): Boolean {
        return status == PlayerStatus.BLACK_JACK
    }

    fun receiveCard(card: Card) {
        if (!isReceivable()) return
        cards.addCard(card)
        updateScoreSet(card)
        updateStatus()
    }

    private fun updateScoreSet(card: Card) {
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

    fun getOptimizedScore(): Int {
        return scoreSet.max()
    }

    private fun updateStatus() {
        val optimizedScore = getOptimizedScore()
        status = PlayerStatus.valuesOf(optimizedScore, status)
    }

    companion object {
        const val INIT_SCORE = 0
    }
}
