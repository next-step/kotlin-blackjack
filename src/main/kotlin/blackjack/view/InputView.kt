package blackjack.view

import blackjack.entity.Game

object InputView {
    private const val DEALER_NAME = "딜러"
    private const val DEALER_MIN_VALUE = 16
    fun getDealerName():String {
        return this.DEALER_NAME
    }


    fun getPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        val players = mutableListOf(DEALER_NAME)
        players.addAll(readln().split(",").map { it.trim() })
        return players
    }

    fun gameStart(players: List<String>) {
        val playersTString = players.joinToString(",")
        println("${playersTString}에게 2장의 카드르르 나누었습니다.")
    }

    fun playerInfo(playerInfos: List<Game>) {
        playerInfos.forEach { playerInfo ->
            val cardInfo = createCardInfos(playerInfo)
            println("${playerInfo.player}카드는 $cardInfo")
        }
    }

    fun isGameContinue(player: String): Boolean {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val continueYN = readln()
        return "Y" == continueYN.uppercase()
    }

    private fun createCardInfos(game: Game): String {
        val cards = game.getPlayerBlackJack().card
        return cards.flatMap { cardMap ->
            cardMap.map { (symbol, card) ->
                String.format("%s%s", card.getValue(), symbol.getType())
            }
        }.joinToString(", ")
    }

    fun isLessThanSixTeen(totalCardValue: Int): Boolean {
        if (totalCardValue <= DEALER_MIN_VALUE) {
            println("딜러는 ${DEALER_MIN_VALUE}이하라 한장의 카드를 더 받았습니다.")
           return true
        } else return false
    }
}
