package blackjack.model

import blackjack.model.participant.BlackjackDealer
import blackjack.model.participant.BlackjackPlayer

fun interface BlackjackPlayersScoreConsumer {

    fun consumePlayers(dealer: BlackjackDealer, players: Collection<BlackjackPlayer>)
}
