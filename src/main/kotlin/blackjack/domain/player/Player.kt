package blackjack.domain.player

import blackjack.domain.bet.Bet
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

open class Player(val name: String, bet: Double, startingCards: List<Card>) {
    val cards: Cards = Cards(startingCards)

    val bet: Bet = Bet(bet)

    init {
        require(startingCards.size == 2) { SHOULD_START_WITH_TWO_CARDS }
    }

    fun addCardToHand(card: Card) {
        this.cards += card
    }

    companion object {
        private const val SHOULD_START_WITH_TWO_CARDS = "플레이어는 두 장의 카드로 게임을 시작해야 합니다."
    }
}
