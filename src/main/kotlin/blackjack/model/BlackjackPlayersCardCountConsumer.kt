package blackjack.model

import blackjack.model.participant.BlackjackDealer
import blackjack.model.participant.BlackjackPlayer

fun interface BlackjackPlayersCardCountConsumer {

    fun consumePlayersCardCount(dealer: BlackjackDealer, players: Collection<BlackjackPlayer>, cardCount: Int)
}
