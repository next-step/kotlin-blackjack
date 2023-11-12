package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

object View {

    fun inputPlayerNames(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return Players(
            readln()
                .split(",")
                .map { Player(it.trim()) }
        )
    }

    fun initialCardDealingComment(players: Players) {
        println("${players.players.joinToString { it.name }}에게 2장의 나누었습니다.")
    }

    fun showCards(players: Players) {
        players.players.forEach { showCard(it) }
    }

    fun showCard(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString { it.cardName() }}")
    }

    fun hitOrStand(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return yesOrNo()
    }

    fun showResults(players: Players) {
        players.players.forEach { showResult(it) }
    }

    private fun showResult(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString { it.cardName() }} - 결과: ${player.getPoints()}")
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
