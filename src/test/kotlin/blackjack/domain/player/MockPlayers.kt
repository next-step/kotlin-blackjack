package blackjack.domain.player

import blackjack.domain.state.mockState

fun mockPlayers(vararg names: String) = names.map(::mockPlayer)
fun mockPlayer(name: String) = Player(
    playerName = PlayerName(
        name = name,
    ),
    state = mockState,
)
