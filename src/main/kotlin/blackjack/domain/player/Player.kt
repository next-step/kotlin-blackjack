package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(val name: String, val cards: Cards = Cards()) {
    fun receiveCard(card: Card) {
        check(canReceive(card)) { RECEIVE_CARD_EXCEPTION_MESSAGE }
        cards.add(card)
    }

    private fun canReceive(card: Card): Boolean {
        if (cards.sum() == Cards.MAX_CARDS_NUM_SUM) {
            return false
        }

        if (cards.sum() + card.number.value > Cards.MAX_CARDS_NUM_SUM) {
            return false
        }

        return true
    }

    companion object {
        private const val RECEIVE_CARD_EXCEPTION_MESSAGE = "카드 숫자 합이 21이 초과하도록 카드를 받을 수 없습니다."
    }
}
