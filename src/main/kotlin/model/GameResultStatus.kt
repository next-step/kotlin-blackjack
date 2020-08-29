package model

class GameResultStatus(player: AbstractPlayer, list: List<AbstractPlayer>) {
    var playerName: PlayerName = player.name
    var win: Int = 0
    var draw: Int = 0
    var lose: Int = 0

    init {
        list.filter { player != it }.forEach {
            when (player.compareResult(it)) {
                BlackJackWinner.WIN -> win++
                BlackJackWinner.DRAW -> draw++
                else -> {
                    lose++
                }
            }
        }
    }
}
