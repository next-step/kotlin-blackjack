package blackjack.domain.player

import blackjack.domain.state.mockState
import blackjack.participant.Player
import blackjack.participant.player.PlayerName

fun mockPlayers(vararg names: String) = names.map(::mockPlayer)
fun mockPlayer(name: String) = Player(
    playerName = PlayerName(
        name = name,
    ),
    state = mockState,
)
