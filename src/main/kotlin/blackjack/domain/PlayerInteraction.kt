package blackjack.domain

interface PlayerInteraction {

    fun getDrawChoice(player: Player): Boolean

    fun printStatus(player: Player)
}
