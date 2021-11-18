package blackjack.view

import blackjack.domain.deck.Cards
import blackjack.domain.gamer.Player

class InputView {

    companion object {
        private const val INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val INPUT_CARD_SIGN = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val DELIMITER = ","

        fun inputPlayers(): List<Player> {
            println(INPUT_PLAYER_MESSAGE)
            val playerNames = readLine()!!.split(DELIMITER)
            return playerNames.map { Player.of(it.trim(), Cards()) }
        }

        fun inputCardSign(player: Player): String {
            println("\n${player.name}$INPUT_CARD_SIGN")
            return readLine()!!
        }
    }
}
