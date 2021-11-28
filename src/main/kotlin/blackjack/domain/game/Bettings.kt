package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

data class Bettings private constructor(val bettings: List<Betting>) {

    init {
        require(bettings.count { it.player is Dealer } == 1)
    }

    fun findBetting(player: Player): Betting? {
        return bettings.find { it.player.getPlayerName() == player.getPlayerName() }
    }

    fun winGamer(player: Player): Bettings {
        val player = findBetting(player)!!
        val winResult = player.winBetting()
        val playerResult = updateBetting(player, winResult)
        val dealer = getDealer()
        val after = dealer.loseDealerBetting(player.credit)
        return playerResult.updateBetting(dealer, after)
    }

    private fun updateBetting(before: Betting, after: Betting): Bettings {
        return Bettings(bettings.replace(after) { it == before })
    }

    private fun getDealer(): Betting {
        return bettings.find { it.player is Dealer }!!
    }

    fun loseGamer(player: Player): Bettings {
        val player = findBetting(player)!!
        val loseResult = player.loseBetting()
        val playerResult = updateBetting(player, loseResult)
        val dealer = getDealer()
        val after = dealer.winDealerBetting(player.credit)
        return playerResult.updateBetting(dealer, after)
    }

    fun winBlackJack(player: Player): Bettings {
        val player = findBetting(player)!!
        val winResult = player.winBlackJackBetting()
        val playerResult = updateBetting(player, winResult)
        val dealer = getDealer()
        val after = dealer.loseBlackJackBetting(player.credit)
        return playerResult.updateBetting(dealer, after)
    }

    companion object {

        fun from(bettings: List<Betting>): Bettings {
            return Bettings(bettings)
        }
    }
}
