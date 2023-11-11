package blackjack.view

import blackjack.model.Player

object View {

    fun inputPlayerNames(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
            .split(",")
            .map { Player(it.trim()) }
    }

    fun initialCardDealingComment(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 나누었습니다.")
    }

    fun showCards(player: Player) {
        println("${player.name}카드: ${player.cardList.joinToString { it.cardName() }}")
    }

    fun hitOrStand(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return yesOrNo()
    }

    fun showResult(player: Player) {
        println("${player.name}카드: ${player.cardList.joinToString { it.cardName() }} - 결과: ${player.getPoint()}")
    }

    private fun yesOrNo(): Boolean = when (readln()) {
        "y" -> true
        "n" -> false
        else -> {
            println("잘못된 입력입니다. 다시 입력해주세요.")
            yesOrNo()
        }
    }
}
