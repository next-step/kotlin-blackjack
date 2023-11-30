package card.deck

import card.CardRank
import card.PlayingCard

class PlayerDeck(override val cardDeck: MutableList<PlayingCard> = mutableListOf()) : CardDeck {

    fun addCard(playingCard: PlayingCard) {
        cardDeck.add(playingCard)
    }

    fun getResultPoint(): Int {
        var point = cardDeck.sumOf { it.getPoint() }

        if (isContainAce()) {
            point += addAcePoint(point)
        }

        return point
    }

    private fun isContainAce(): Boolean {
        return cardDeck.any { it.getCardRank() == CardRank.ACE }
    }

    private fun addAcePoint(point: Int): Int {
        return if (point < 11) {
            ADD_ACE_POINT
        } else {
            ADD_ACE_POINT_NONE
        }
    }

    companion object {
        fun create() = PlayerDeck(mutableListOf())
        private const val ADD_ACE_POINT = 10
        private const val ADD_ACE_POINT_NONE = 0
    }
}
