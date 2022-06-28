package blackjack

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

    val gamers: List<UserRole>
        get() = blackjackGameElement.gamers

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
        requestView.moreCardToGamer(player.name)
        return when (readln()) {
            "y" -> player.draw(blackjackGameElement.draw())
            else -> player.stand()
        }
    }
}
