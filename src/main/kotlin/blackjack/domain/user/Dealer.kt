package blackjack.domain.user

import blackjack.domain.card.Card

/**
 * 딜러
 * Created by Jaesungchi on 2022.06.15..
 */
class Dealer(initCards: List<Card>) : User(DEALER_NAME, initCards) {

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
