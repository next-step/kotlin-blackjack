package view

import BlackJackGame
import Player

class InputView {

    fun inputBlackJack() {
        val players = inputPlayerNames()
        val blackJackGame = BlackJackGame(players)
    }

    private fun inputPlayerNames(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val inputPlayerNames = readlnOrNull()
        return inputPlayerNames
                ?.split(",")
                ?.map { Player(it) }
                ?: throw IllegalArgumentException("게임에 참여할 사람의 이름은 필수 입니다.")
    }
}