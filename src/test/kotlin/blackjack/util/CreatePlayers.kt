package blackjack.util

import blackjack.domain.Player
import blackjack.domain.MutableCards
import blackjack.domain.PlayerName
import blackjack.domain.Players

fun createPlayers(vararg names: String): Players {
    return Players(names.map { Player(PlayerName(it), MutableCards(mutableListOf())) })
}
