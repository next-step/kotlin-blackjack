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
        require(bettings.count { it.player is Dealer } == DEALER_COUNT)
    }

    fun findBetting(player: Player): Betting? {
        return bettings.find { it.player.getPlayerName() == player.getPlayerName() }
    }

    fun winGamer(player: Player): Bettings {
        val (player, dealer) = findPlayers(player)

        val winResult = player.winBetting()
        val playerResult = updateBetting(player, winResult)
        val after = dealer.loseDealerBetting(player.credit)
        return playerResult.updateBetting(dealer, after)
    }

    fun loseGamer(player: Player): Bettings {
        val (player, dealer) = findPlayers(player)

        val loseResult = player.loseBetting()
        val playerResult = updateBetting(player, loseResult)

        val afterBetting = dealer.winDealerBetting(player.credit)
        return playerResult.updateBetting(dealer, afterBetting)
    }

    fun winBlackJack(player: Player): Bettings {
        val (player, dealer) = findPlayers(player)

        val winResult = player.winBlackJackBetting()
        val playerResult = updateBetting(player, winResult)
        val afterBetting = dealer.loseBlackJackBetting(player.credit)
        return playerResult.updateBetting(dealer, afterBetting)
    }

    private fun findPlayers(player: Player): Pair<Betting, Betting> {
        val player = findBetting(player)!!
        val dealer = getDealer()
        return Pair(player, dealer)
    }

    private fun updateBetting(before: Betting, after: Betting): Bettings {
        return Bettings(bettings.replace(after) { it == before })
    }

    private fun getDealer(): Betting {
        return bettings.find { it.player is Dealer }!!
    }

    companion object {
        private const val DEALER_COUNT = 1

        fun from(bettings: List<Betting>): Bettings {
            return Bettings(bettings)
        }
    }
}
