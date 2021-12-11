package blackjack.play

import blackjack.entity.Player

class GamePlayUsers(
    players: List<Player>
) {

    private val _playUsers = players
    val playUsers get() = _playUsers.toList()

    override fun toString(): String {
        return "$playUsers"
    }

    fun playing() {
        for (player in playUsers) {
            Controller.playing(player)
        }
    }
}
