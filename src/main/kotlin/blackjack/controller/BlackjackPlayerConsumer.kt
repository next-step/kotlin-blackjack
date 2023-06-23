package blackjack.controller

import blackjack.model.BlackjackPlayer

fun interface BlackjackPlayerConsumer {

    fun consumePlayer(player: BlackjackPlayer)
}
