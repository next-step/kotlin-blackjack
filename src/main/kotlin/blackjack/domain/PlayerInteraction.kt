package blackjack.domain

interface PlayerInteraction {

    fun showInitDraw(initCardCount: Int, players: List<Player>)
    fun askHit(player: Player): Boolean
    fun showPlayer(player: Player)
}
