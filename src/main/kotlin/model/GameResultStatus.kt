package model

class GameResultStatus(player: AbstractPlayer, list: List<AbstractPlayer>) {
    var playerName: PlayerName = player.name
    var win: Int = 0
    var draw: Int = 0
    var lose: Int = 0

    init {
        list.filter { player != it }.forEach {
            player.compareResult(it)
            if(player.compareResult(it) == BlackJackWinner.WIN) {
                win++
            } else if (player.compareResult(it) == BlackJackWinner.DRAW) {
                draw++
            } else {
                lose++
            }
        }
    }

}