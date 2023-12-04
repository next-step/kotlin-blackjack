package blackjack.view

class InputView {
    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun getPlayerBet(playerNames: List<String>): List<Int> {
        return playerNames.map {
            println("${it}의 배팅 금액은?")
            readln().toInt()
        }
    }

    fun getWantToDraw(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        var wantToDraw = ""
        while (!(wantToDraw == "y" || wantToDraw == "n")) {
            wantToDraw = readln()
        }

        return wantToDraw == "y"
    }
}
