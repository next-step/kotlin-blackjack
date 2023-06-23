package blackjack.controller

import blackjack.model.BlackjackPlayers

fun interface BlackjackPlayersCardCountConsumer {

    fun consumePlayersCardCount(players: BlackjackPlayers, cardCount: Int)
}
