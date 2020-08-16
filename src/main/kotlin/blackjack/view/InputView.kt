package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Dealer
import blackjack.domain.HIT
import blackjack.domain.STAY
import blackjack.domain.LIMIT_HIT_NUMBER

object InputView {
    fun getPlayers(): String {
        println("플레이어명을 입력해주세요.")
        return readLine()!!
    }

    fun getHitOrStay(player: Player): String {
        if (player is Dealer) return getHitOrStayDealer(player)
        println("\n${player.name}은 한장의 카드를 더 받겠습니까?(예는 $HIT, 아니오는 $STAY, $HIT,$STAY 으로만 입력해주세요. )")
        return readLine()!!
    }

    private fun getHitOrStayDealer(dealer: Dealer): String {
        if (dealer.isHit) println("\n${dealer.name}는 ${LIMIT_HIT_NUMBER}이하라 카드를 더 받습니다.")
        return if (dealer.isHit) HIT else STAY
    }

    fun getBetMoneyFromPlayer(player: Player): String {
        println("${player.name}의 배팅 금액은?")
        return readLine()!!
    }
}
