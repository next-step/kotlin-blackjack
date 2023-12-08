package card.deck

import card.CardRank
import card.PlayingCard

class Hands(val cardList: MutableList<PlayingCard> = mutableListOf()) {

    fun addCard(playingCard: PlayingCard) {
        cardList.add(playingCard)
    }

    fun getResultPoint(): Int {
        var point = cardList.sumOf { it.getPoint() }

        if (isContainAce()) {
            point += addAcePoint(point)
        }

        return point
    }

    fun cardDeckSize() = cardList.size

    private fun isContainAce(): Boolean {
        return cardList.any { it.cardRank == CardRank.ACE }
    }

    private fun addAcePoint(point: Int): Int {
        return if (point <= 11) {
            ADD_ACE_POINT
        } else {
            ADD_ACE_POINT_NONE
        }
    }

    companion object {
        fun create() = Hands(mutableListOf())
        private const val ADD_ACE_POINT = 10
        private const val ADD_ACE_POINT_NONE = 0
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (card in cardList) {
            sb.append("$card, ")
        }
        sb.deleteCharAt(sb.lastIndex - 1)
        return sb.toString()
    }
}
