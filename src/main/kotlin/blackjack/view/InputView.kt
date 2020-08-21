package blackjack.view

import blackjack.domain.BetMoney
import blackjack.domain.BetMoneyMap
import blackjack.domain.Dealer
import blackjack.domain.HIT
import blackjack.domain.LIMIT_HIT_NUMBER
import blackjack.domain.MAX_MONEY
import blackjack.domain.MIN_MONEY
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.STAY

object InputView {
    private fun getPlayerNames(): String {
        println("플레이어명을 입력해주세요.")
        return readLine()!!
    }

    fun getPlayers(): Players {
        val players: Players? = Players.from(getPlayerNames())
        return players ?: getPlayers()
    }

    fun getHitOrStay(player: Player): String {
        if (player is Dealer) return getHitOrStayDealer(player)
        println("\n${player.name}은 한장의 카드를 더 받겠습니까?(예는 $HIT, 아니오는 $STAY)")
        return readLine()!!
    }

    private fun getHitOrStayDealer(dealer: Dealer): String {
        if (dealer.isHit) println("\n${dealer.name}는 ${LIMIT_HIT_NUMBER}이하라 카드를 더 받습니다.")
        return if (dealer.isHit) HIT else STAY
    }

    private fun getBetMoney(playerName: String): BetMoney {
        println("${playerName}의 배팅 금액은? ($MIN_MONEY ~ $MAX_MONEY 사이의 금액을 입력해주세요.)")
        val betMoney: BetMoney? = BetMoney.of(readLine()!!)
        return betMoney ?: getBetMoney(playerName)
    }

    fun getBetMoneyMap(players: List<Player>): BetMoneyMap {
        val mutableMap = mutableMapOf<String, BetMoney>()
        players.filterNot { it is Dealer }.forEach { mutableMap[it.name] = getBetMoney(it.name) }
        return BetMoneyMap(mutableMap)
    }
}
