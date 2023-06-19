package blackjack.domain.player

import blackjack.domain.state.mockHitState
import blackjack.participant.ParticipantName
import blackjack.participant.Player

fun mockPlayers(vararg names: String) = names.map(::mockPlayer)
fun mockPlayer(name: String) = Player(
    participantName = ParticipantName(
        name = name,
    ),
    state = mockHitState,
)
