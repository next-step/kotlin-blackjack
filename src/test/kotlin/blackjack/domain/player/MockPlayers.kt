package blackjack.domain.player

import blackjack.domain.bet.Bet
import blackjack.domain.participant.ParticipantName
import blackjack.domain.participant.Player
import blackjack.domain.state.mockHitState

fun mockPlayers(vararg names: String) = names.map(::mockPlayer)

fun mockPlayer(name: String) = Player(
    participantName = ParticipantName(
        name = name,
    ),
    state = mockHitState,
    bet = Bet(),
)
