package blackjack.view

import blackjack.entity.Game

object InputView {
    fun getPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    fun gameStart(players: List<String>) {
        val playersTString = players.joinToString(",")
        println("${playersTString}에게 2장의 카드르르 나누었습니다.")
    }

    fun playerInfo(playerInfos: List<Game>) {
        playerInfos.forEach {
                playerInfo ->
            val cardInfo = createCardInfos(playerInfo)
            println("${playerInfo.player}카드는 $cardInfo")
        }
    }

    fun gameContinue(player: String): String {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }

    private fun createCardInfos(game: Game): String {
        val blackJack = game.getPlayerBlackJack()
        return blackJack.card.map { (CardSymbol, Card) -> String.format("%s%s", Card.getValue(), CardSymbol.getTye()) }.joinToString(", ")
    }
}
