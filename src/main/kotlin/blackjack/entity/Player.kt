package blackjack.entity

import blackjack.domain.BlackJackGame.INIT_FACE_UP
import blackjack.entity.PlayerHand.Companion.BUST_LIMIT_VALUE

class Player(val name: String, val betAmount: Int, initialCards: List<CardInfo>) {
    val hand: PlayerHand = PlayerHand(initialCards)

    fun isPlayerFirstBlackJack(): Boolean = this.hand.getCardsCount() == INIT_FACE_UP && this.hand.getTotalCardValue() == BUST_LIMIT_VALUE

    fun isBlackJack(): Boolean = this.hand.getTotalCardValue() == BUST_LIMIT_VALUE
}
