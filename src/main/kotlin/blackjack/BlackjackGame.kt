package blackjack

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerVo
import blackjack.domain.player.UserRole

class BlackjackGame(
    private val blackjackGameElement: BlackjackGameElement,
    private val requestView: RequestView
) {

    fun play(): List<UserRole> {
        return blackjackGameElement.gamers.asSequence()
            .filter { !it.isDealer() }
            .map { playPlayerTurn(it) }
            .plus(playDealerTurn())
            .toList()
    }

    fun getGamers(): List<PlayerVo> {
        return blackjackGameElement.gamers
            .map { PlayerVo(it.userSetting.name, it.cards.map { c -> c.toString() }.toString()) }
    }

    private fun playPlayerTurn(player: UserRole): UserRole {
        var p = player
        while (!p.isFinish()) {
            p = deal(p) as Player
        }
        return p
    }

    private fun playDealerTurn(): UserRole {
        var dealer = blackjackGameElement.gamers.first { it.isDealer() }
        while (!dealer.isFinish()) {
            requestView.moreCardToDealer()
            dealer = dealer.draw(blackjackGameElement.draw())
        }
        return dealer
    }

    private fun deal(player: UserRole): UserRole {
        requestView.moreCardToGamer(player.userSetting.name)
        return when (readln()) {
            "y" -> player.draw(blackjackGameElement.draw())
            else -> player.stand()
        }
    }
}
