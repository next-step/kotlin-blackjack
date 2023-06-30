package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.card.CardHold.Companion.BLACKJACK_CARD_POINT
import blackjack.domain.card.CardHold.Companion.BLACKJACK_CARD_SIZE
import blackjack.domain.card.CardRank
import blackjack.domain.card.Deck
import blackjack.domain.rule.Money

sealed class GameMember {
    abstract val name: String
    abstract val cardHold: CardHold
    abstract var money: Money
        protected set

    abstract fun canDraw(): Boolean

    fun getPoints(): Int {
        val sum = cardHold.getTotalPoints()
        if (cardHold.getAllCards().any { card -> card.rank == CardRank.ACE } && sum <= BLACKJACK_CARD_POINT) {
            return sum + CardRank.ACE.getSoftHand()
        }
        return sum
    }

    fun getCardHoldSize(): Int {
        return cardHold.getCardsTotalSize()
    }

    fun drawCard(deck: Deck) {
        if (!canDraw()) {
            throw IllegalArgumentException("카드 숫자의 합이 21을 초과합니다.")
        }

        val card = deck.draw()
        if (card != null) {
            cardHold.add(card)
        }
    }

    fun betMoney(amount: Int): Money {
        money -= Money(amount)
        return Money(amount)
    }

    fun getBettingMoney(): Money {
        return money.getAmount()
    }

    fun winMoney(amount: Money) {
        money += amount
    }

    fun isBlackJack(): Boolean {
        return getPoints() == BLACKJACK_CARD_POINT && getCardHoldSize() == BLACKJACK_CARD_SIZE
    }

    fun isExceedCardPoint(): Boolean {
        return getCardHoldSize() > BLACKJACK_CARD_POINT
    }
}
