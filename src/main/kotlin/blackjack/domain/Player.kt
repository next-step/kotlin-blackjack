package blackjack.domain

import java.lang.Integer.max

class Player(
    val cards: Cards = Cards(),
    var score: Int = 0,
    val name: String
) {
    private var status: PlayerStatus = PlayerStatus.GET

    init {
        cards.card.map {
            updateScore(it)
            updateStatus()
        }
    }

    fun isGetCardPossible(): Boolean {
        return status == PlayerStatus.GET
    }

    fun isBlackJack(): Boolean {
        return status == PlayerStatus.BLACK_JACK
    }

    fun getCard(card: Card) {
        if (!isGetCardPossible()) return
        cards.addCard(card)
        updateScore(card)
        updateStatus()
    }

    fun updateScore(card: Card) {
        val cardScore = card.number.score
        with(cardScore.value) {
            score += if (this.size > 1) {
                this.map { it.value }.reduce { calculateScore, curScore ->
                    val endScore = score + curScore > PlayerStatus.BLACK_JACK_SCORE
                    if (!endScore) max(calculateScore, curScore) else calculateScore
                }
            } else {
                this.first().value
            }
        }
    }

    fun updateStatus() {
        status = PlayerStatus.status(status, score)
    }
}
