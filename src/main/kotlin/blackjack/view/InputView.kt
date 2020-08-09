package blackjack.view

import blackjack.domain.Player

object InputView {
    fun getPlayers(): String {
        println("플레이어명을 입력해주세요.")
        val playerNames = readLine()!!
        require(playerNames.isNotEmpty())
        return playerNames
    }

    fun getHitOrStay(player: Player): String {
        println("\n${player.name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val hitOrStay = readLine()!!
        require(hitOrStay.isNotEmpty())
        return hitOrStay
    }
}
