package blackjack.view

import kotlin.system.exitProcess

object Input {
    fun names(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()?.split(",") ?: exitProcess(-1)
    }

    fun ask(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(y/n)")
        val ask = readLine().toString()
        if (!(ask == "y" || ask == "n")) exitProcess(-1)
        return ask == "y"
    }
}
