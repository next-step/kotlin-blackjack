package blackjack.bet.view

object BetInputView {
    fun chargeWallet(name: String): Int {
        println("\n${name}의 배팅 금액은?")
        val input = readln()
        require(input.toIntOrNull() != null) { "숫자를 입력해주세요!" }
        return input.toInt()
    }
}
