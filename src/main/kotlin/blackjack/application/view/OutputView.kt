package blackjack.application.view

import blackjack.Player

class OutputView {

    fun showInputPlayerNames() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun showDefaultDrawToPlayers(playerList: List<Player>) {
        println("${playerList.joinToString { it.name }}에게 2장의 나누었습니다.")
        playerList.forEach {
            showPlayerCardList(it)
        }
    }

    fun showPlayerCardList(it: Player) {
        println("${it.name}카드: ${it.cardList.joinToString { card -> card.toString() }}")
    }

    fun showDoYouWantCard(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun showPlayersInfo(playerList: List<Player>) {
        playerList.forEach {
            println("${it.name}카드: ${it.cardList.joinToString { card -> card.toString() }} - 결과: ${it.score}")
        }
    }
}
