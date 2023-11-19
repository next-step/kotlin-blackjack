package blackJack.domain

import blackJack.error.ErrorMessage

class Card(val suit: Suit, val rank: Rank) {

    init {
        require(suit in Suit.values()) { ErrorMessage.WRONG_SUIT.message }
        require(rank in Rank.values()) { ErrorMessage.WRONG_RANK.message }
    }

    companion object {
        fun drawCard(): Card {
            return Card(Suit.randomizeSuit(), Rank.randomizeRank())
        }
    }
}
