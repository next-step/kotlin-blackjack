package blackjack

object OutputView {
    private const val SEPARATOR = ", "

    fun showPlayersCard(players: List<Player>) {
        println("${players.joinToString(SEPARATOR) { it.getName() }}에게 2장의 카드를 나누어 주었습니다.")
        players.forEach {
            getUserCard(it)
        }
    }

    fun getUserCard(player: Player) {
        println("${player.getName()}카드: ${player.getCards()}")
    }

    fun getWinner(players: List<Player>) {
        players.forEach {
            println("${it.getName()}카드: ${it.getCards()} - 결과: ${it.getScore()}")
        }
    }
}
