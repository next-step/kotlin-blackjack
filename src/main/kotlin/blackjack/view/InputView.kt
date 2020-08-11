package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.HIT
import blackjack.domain.LIMIT_HIT_NUMBER
import blackjack.domain.Player
import blackjack.domain.STAY

object InputView {
    fun getPlayers(): String {
        println("플레이어명을 입력해주세요.")
        val playerNames = readLine()!!
        require(playerNames.isNotEmpty())
        return playerNames
    }

    fun getHitOrStay(player: Player): String {
        if (player is Dealer) return getHitOrStayDealer(player)
        println("\n${player.name}은 한장의 카드를 더 받겠습니까?(예는 $HIT, 아니오는 $STAY)")
        val hitOrStay = readLine()!!
        require(hitOrStay.isNotEmpty())
        return hitOrStay
    }

    private fun getHitOrStayDealer(dealer: Dealer): String {
        if (dealer.isHit) println("\n${dealer.name}는 ${LIMIT_HIT_NUMBER}이하라 카드를 더 받습니다.")
        return if (dealer.isHit) HIT else STAY
    }
}
