package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARD_COUNT

interface Player {
    val playerInfo: PlayerInfo
    val cards: Cards

    fun getPlayerName(): String = playerInfo.getName()

    fun getBettingAmount(): BettingAmount = playerInfo.getBettingAmount()

    fun initialCard(deck: Deck): Player

    fun hit(deck: Deck): Player

    fun canHit(): Boolean = !isBurst()

    fun countingCard(): Int = cards.countingCard()

    fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE

    fun isBlackjack(): Boolean = cards.count() == INITIAL_CARD_COUNT && countingCard() == BLACKJACK_SCORE
}
