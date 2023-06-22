package controller

import domain.game.BlackjackGame
import domain.player.Player
import domain.player.Players
import domain.state.State

class BlackjackGameController(private val game: BlackjackGame) {
    fun initGame(playerNames: List<String>): Players {
        return game.initGame(playerNames)
    }

    fun isTerminatedPlayer(player: Player): Boolean {
        return game.isTerminatedPlayer(player)
    }

    fun issueCard(player: Player): State {
        return game.issueCard(player)
    }

    fun stopIssueCard(player: Player): State {
        return game.stopIssueCard(player)
    }
}
