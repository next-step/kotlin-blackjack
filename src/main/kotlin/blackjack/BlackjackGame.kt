package blackjack

class BlackjackGame(private val blackjackGameElement: BlackjackGameElement) {

    fun play(): List<UserRole> {
        val players = blackjackGameElement.gamers.filter { !it.isDealer() }
        val results = mutableListOf<UserRole>()
        for (player in players) {
            results.add(playPlayerTurn(player))
        }

        results.add(playDealerTurn())
        return results.toList()
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
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            dealer = dealer.draw(blackjackGameElement.draw())
        }
        return dealer
    }

    private fun deal(player: UserRole): UserRole {
        println("%s 님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".format(player.name))
        return when (readln()) {
            "y" -> player.draw(blackjackGameElement.draw())
            else -> player.stand()
        }
    }
}
