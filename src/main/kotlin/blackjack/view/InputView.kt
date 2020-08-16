package blackjack.view

import blackjack.model.player.Dealer
import blackjack.model.player.Gamer
import blackjack.model.player.Player
import blackjack.model.player.Players

object InputView {
    private const val PLAYER_NAME_DELIMITER = ","
    private const val PLAYER_ANSWER_YES = "y"
    private const val PLAYER_ANSWER_NO = "n"

    fun getPlayer(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val names = readLine().toString().split(PLAYER_NAME_DELIMITER)
        val gamers = names.map { Gamer(it) }

        val players = mutableListOf<Player>()

        players.add(Dealer())
        players.addAll(gamers)

        return Players(players)
    }

    fun askToDraw(player: Gamer): Boolean {
        println("${player.name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        var answer = readLine().toString()

        while (answer != PLAYER_ANSWER_YES && answer != PLAYER_ANSWER_NO) {
            println("올바른 답변이 아닙니다.")

            answer = readLine().toString()
        }

        return answer == PLAYER_ANSWER_YES
    }
}
