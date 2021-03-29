package blackjack.ui

import kotlin.IllegalArgumentException

object InputView {

    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉽표 기준으로 분리")
        val names = readLine()
        return names?.split(",") ?: throw IllegalArgumentException()
    }

    fun selectDrawCard(name: String): String {
        println("${name}은 한장의 카드를 더 받겠습니까?")
        return readLine() ?: throw IllegalArgumentException("Y/N 으로 대답해주세요")
    }
}