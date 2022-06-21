package blackjack.domain

import blackjack.domain.enums.CardPoint
import java.util.LinkedList
import java.util.Queue

class Dealer() : Player("딜러") {
    private val cardPack: Queue<Card> = LinkedList()
    override val cards = mutableListOf<Card>()

    init {
        Card.pack().shuffled().map { card -> cardPack.add(card) }
    }

    fun give(): Card {
        return cardPack.poll()
    }

    fun shareCards(): List<Card> {
        return BASIC_CARD_RANGE.map { cardPack.poll() }
    }

    fun getMoreCard(): Boolean {
        if (needCard()) {
            cards.add(give())
            return true
        }

        return false
    }

    override fun isWinner(players: List<Player>): Boolean {
        val myScore = score()
        if (myScore > CardPoint.BLACK_JACK_SCORE) {
            return false
        }

        return myScore > players[0].score()
    }

    private fun needCard(): Boolean {
        return super.needCard(chooseNeedCard())
    }

    private fun chooseNeedCard(): String {
        val cardScore = score()
        return when {
            cardScore <= 16 -> "y"
            cardScore >= 17 -> "n"
            else -> throw IllegalArgumentException("카드 숫자 합이 비정상입니다.")
        }
    }

    companion object {
        private val BASIC_CARD_RANGE = 1..2
    }
}
