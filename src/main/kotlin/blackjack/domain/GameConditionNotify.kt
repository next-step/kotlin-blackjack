package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.Players

interface GameConditionNotify {
    fun giveDefaultCardsToPlayerDone(players: Players)
    fun isNeedMoreCard(player: Player): Boolean
    fun giveCardToPlayerDone(player: Player)
    fun finishBlackJackGame(players: Players)
}
