package blackjack.bet.view

object BetInputView : InputView {
    override fun chargeWallet(name: String): Int {
        println("\n${name}의 배팅 금액은?")
        val input = readln()
        require(input.toIntOrNull() != null) { "숫자를 입력해주세요!" }
        return input.toInt()
    }

    override fun getPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val inputNames = readln()
        require(inputNames.isNotBlank()) { "참가자의 이름을 올바르게 입력해주세요. $inputNames" }
        return inputNames.split(",")
    }

    override fun wantToHit(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val continuePlaying = readln()
        require(continuePlaying == "y" || continuePlaying == "n") { "y 혹은 n를 입력해주세요." }
        return continuePlaying == "y"
    }
}
