package blackjack.domain.view

object InputView {

    private const val ENTER_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)"
    private const val ENTER_HIT_OR_NOT = "\n%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"

    fun readPlayers(): PlayerNamesInput {
        println(message = ENTER_PLAYERS)
        return PlayerNamesInput(text = readln())
    }

    fun readHitOrNot(playerName: String): Boolean {
        println(message = ENTER_HIT_OR_NOT.format(playerName))

        val inputCommand = readln().uppercase()

        return HitCommand.valueOf(value = inputCommand) == HitCommand.Y
    }
}
