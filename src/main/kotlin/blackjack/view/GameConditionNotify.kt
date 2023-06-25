package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

interface GameConditionNotify {
    fun showPlayerCardSet(players: Players)
    fun isNeedMoreCard(player: Player): Boolean
    fun showPlayerCards(player: Player)
    fun showGameResult(players: Players)
}
