package blackjack

object OutputView {
    fun showPlayersCard(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장의 카드를 나누어 주었습니다.")
        players.forEach {
            println("${it.name}카드: ${it.getCards().userCards}")
        }
    }
}
