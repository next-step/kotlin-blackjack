package blackjack.domain.player

import blackjack.domain.behavior.mockState

fun mockPlayers(vararg names: String) = names.map { Player(name = it, state = mockState) }
