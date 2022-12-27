package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARD_COUNT

data class Dealer(
    override val playerInfo: PlayerInfo = DEALER_INFO,
    override val cards: Cards = Cards()
) : Player {
    constructor(cards: Cards) : this(DEALER_INFO, cards)

    override fun initialCard(deck: Deck): Dealer {
        return copy(cards = cards + deck.draw(INITIAL_CARD_COUNT))
    }

    override fun hit(deck: Deck): Dealer {
        check(canHit()) { "카드를 받을 수 없습니다." }
        return this.copy(cards = cards + deck.draw())
    }

    override fun canHit(): Boolean = cards.countingCard() < DEALER_HIT_RULE_SCORE

    companion object {
        private const val NAME = "딜러"
        val DEALER_INFO: PlayerInfo = PlayerInfo(NAME)
        const val DEALER_HIT_RULE_SCORE = 17
    }
}
