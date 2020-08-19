package blackjack

object OutputView {
    private const val SEPARATOR = ", "

    fun showPlayersCard(players: List<Gamer>) {
        val user = players.filterIsInstance<Player>()
        println("딜러와 ${user.joinToString(SEPARATOR) { it.name }}에게 2장의 카드를 나누어 주었습니다.")
        println("딜러:${players[0].cards}")
        user.forEach {
            showUserCards(it)
        }
    }

    fun showUserCards(player: Gamer) {
        println("${player.name}카드: ${player.cards}")
    }

    fun showWinners(players: List<Gamer>) {
        players.forEach {
            println("${it.name}카드: ${it.cards} - 결과: ${it.cards.sumCardNumbers()}")
        }
    }

    fun showDealerStatus() {
        print("")
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
