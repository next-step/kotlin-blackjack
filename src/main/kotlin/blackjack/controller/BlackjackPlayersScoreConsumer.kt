package blackjack.controller

import blackjack.model.BlackjackPlayers

fun interface BlackjackPlayersScoreConsumer {

    fun consumePlayers(players: BlackjackPlayers)
}
