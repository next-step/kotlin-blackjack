package blackjack.view

import blackjack.entity.BlackJack
import blackjack.entity.Player

object InputView {
    private const val DEALER_NAME = "딜러"
    fun getDealerName(): String {
        return this.DEALER_NAME
    }

    fun getPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    fun isGameContinue(player: String): Boolean {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val continueYN = readln()
        return "Y" == continueYN.uppercase()
    }

    fun playerInfo(player: Player) {
        val cardInfo = createCardInfos(player.getPlayerBlackJack())
        println("${player.name}카드는 $cardInfo")
    }

    private fun createCardInfos(blackJack: BlackJack): String {
        val cards = blackJack.cards
        return cards
            .filter { it.second }
            .flatMap { (cardMap, _) ->
                cardMap.map { (symbol, card) ->
                    String.format("%s%s", card.getValue(), symbol.getType())
                }
            }
            .joinToString(", ")
    }

    fun dealerAddCardComment(dealerName: String) {
        println("${dealerName}는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
