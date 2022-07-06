package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.Hand

sealed interface Player {

    val name: String

    val hand: Hand

    fun receive(card: Card)
}
