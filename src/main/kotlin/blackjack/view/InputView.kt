package blackjack.view

class InputView {
    fun getPlayers(): List<String> {
        println("게임에 참여할 사람이 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln()
        return input.split(",")
    }
}
