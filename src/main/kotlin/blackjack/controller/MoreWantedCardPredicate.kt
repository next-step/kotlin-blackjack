package blackjack.controller

import blackjack.model.BlackjackPlayer

fun interface MoreWantedCardPredicate {

    fun isWantedMorePredicate(player: BlackjackPlayer): Boolean
}
