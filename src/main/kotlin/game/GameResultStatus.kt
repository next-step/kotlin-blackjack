package game

import model.AbstractPlayer
import model.PlayerName

abstract class GameResultStatus(player: AbstractPlayer) {
    var playerName: PlayerName = player.name
        private set
    var win: Int = 0
    var draw: Int = 0
    var lose: Int = 0
}
