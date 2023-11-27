package blackjack.view

class UserInputView : InputView {
    override fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    override fun getPlayerCommand(playerName: String): InputViewCommand {
        println("${playerName}은 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val cmd = readln().trim()
        return InputViewCommand.get(cmd)
    }
}
