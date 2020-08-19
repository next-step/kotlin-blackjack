package blackjack.model.card

import blackjack.model.player.BLACKJACK_MAX_NUMBER
import blackjack.model.player.FIRST_TURN_CARD_COUNT
import blackjack.model.status.CardStatus

class PlayerCards {
    private val _cards = mutableListOf<Card>()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun getCardStatus(): CardStatus {
        return CardStatus.getStatus(getPoint())
    }

    fun getCount(): Int {
        return _cards.size
    }

    fun getDisplayCards(): String {
        return _cards.joinToString(separator = ",") { "${it.denomination.title}${it.suit.title}" }
    }

    fun getPoint(): Int {
        val pointSum = _cards.sumBy { it.denomination.point }
        val pointSumOptional = _cards.sumBy { it.denomination.pointOptional }

        if (pointSumOptional <= BLACKJACK_MAX_NUMBER) return pointSumOptional

        return pointSum
    }

    fun isBlackJack(): Boolean {
        return _cards.size == FIRST_TURN_CARD_COUNT && getCardStatus() == CardStatus.BLACKJACK
    }

    fun isBust(): Boolean {
        return getCardStatus() == CardStatus.BUST
    }
}
