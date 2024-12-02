package blackjack

object OutputView {
    fun printDefaultPlayerCards(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.getCards().joinToString { it.number.symbol + it.mark.korName }}")
    }

    fun printResult(players: List<Player>) {
        players.forEach { "${printPlayerCards(it)} - 결과: ${it.getCardsMaxSum()}" }
    }
}
