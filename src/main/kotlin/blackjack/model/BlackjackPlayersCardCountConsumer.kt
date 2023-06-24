package blackjack.model

fun interface BlackjackPlayersCardCountConsumer {

    fun consumePlayersCardCount(players: BlackjackPlayers, cardCount: Int)
}
