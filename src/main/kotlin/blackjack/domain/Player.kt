package blackjack.domain

import kotlin.math.max

class Player(val name: Name = Name(), val cards: Cards = Cards(), var score: Int = INIT_SCORE) {
    private var status: PlayerStatus = PlayerStatus.RECEIVE

    init {
        cards.value.map { updateScore(it) }
        updateStatus()
    }

    fun isReceivable(): Boolean {
        return status == PlayerStatus.RECEIVE
    }

    fun isBlackJack(): Boolean {
        return status == PlayerStatus.BLACK_JACK
    }

    fun receiveCard(card: Card) {
        if (!isReceivable()) return
        cards.addCard(card)
        updateScore(card)
        updateStatus()
    }

    private fun updateStatus() {
        status = PlayerStatus.valuesOf(score, status)
    }

    private fun updateScore(card: Card) {
        val cardScores = card.denom.scores
        with(cardScores.value) {
            score += if (this.size > 1) {
                this.map { it.value }.reduce { optimizeScore, curScore ->
                    val isBustScore = score + curScore > PlayerStatus.BLACK_JACK_SCORE
                    if (!isBustScore) max(optimizeScore, curScore) else optimizeScore
                }
            } else {
                this.first().value
            }
        }
    }

    companion object {
        private const val INIT_SCORE = 0
    }
}
