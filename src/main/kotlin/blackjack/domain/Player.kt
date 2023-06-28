package blackjack.domain

import java.lang.Integer.max

class Player(
    var score: MutableList<Int> = mutableListOf(0),
    val name: String
) {
    private var status: PlayerStatus = PlayerStatus.HIT
    val cards: Cards = Cards()

    init {
        cards.card.map {
            updateScore(it)
            updateStatus()
        }
    }

    fun isGetCardPossible(): Boolean {
        return status == PlayerStatus.HIT
    }

    fun isBlackJack(): Boolean {
        return status == PlayerStatus.BLACK_JACK
    }

    fun receiveCard(card: Card) {
        if (!isGetCardPossible()) return
        cards.addCard(card)
        updateScore(card)
        updateStatus()
    }

    fun updateScore(card: Card) {
        val cardScore = card.number.score
        val calculateScore = score.flatMap { value ->
            if (card.number == NumberType.ACE) {
                cardScore.scores.map { value + it.value }
            } else {
                listOf(value + cardScore.scores.first().value)
            }
        }

        if (isGetCardPossible()) {
            score.clear()
            score.add(calculateScore.min())
        }
    }

    fun updateStatus() {
        status = PlayerStatus.status(status, score.max())
    }
}
