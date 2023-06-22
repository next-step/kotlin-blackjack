package blackjack.view

import blackjack.domain.Player

object InputView {
    fun getPlayers(): Array<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val inputNames = readln()
        require(inputNames.isNotBlank()) { "참가자의 이름을 올바르게 입력해주세요. $inputNames" }
        val playerNames = inputNames.split(",")
        return playerNames.map { Player(it) }.toTypedArray()
    }

    fun wantToHit(name: String): Boolean {
        var continuePlaying: String
        var attempts = 0
        do {
            if (attempts > 0) {
                val chance = 3 - attempts
                println("y 혹은 n를 입력해주세요. [남은 기회 : $chance]")
            }
            println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            continuePlaying = readln()
            attempts++
        } while ((continuePlaying != "y" && continuePlaying != "n") && attempts < 3)

        require(continuePlaying == "y" || continuePlaying == "n") { "y 혹은 n를 입력해주세요." }
        return continuePlaying == "y"
    }
}
