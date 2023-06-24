package blackjack.ui

class GameInputProxy(private val target: GameInput) : GameInput by target {

    override fun requestPlayerBet(name: String): Double {
        val amount = target.requestPlayerBet(name)

        return if (amount > 0) {
            amount
        } else {
            println("0 이상의 유효한 금액을 입력해주세요. Input: $amount")
            requestPlayerBet(name)
        }
    }

    override fun requestPlayerNames(): List<String> =
        target.requestPlayerNames().ifEmpty {

            println("유효한 참가자 이름을 입력해 주세요(쉼표 기준으로 분리)")
            requestPlayerNames()
        }

    override fun requestConfirmDrawCard(name: String): String =
        when (val confirmDrawCard = target.requestConfirmDrawCard(name).trim().lowercase()) {
            in YES_OR_NO -> confirmDrawCard
            else -> {
                println("유효한 응답을 입력해주세요.(예는 y, 아니오는 n)")
                requestConfirmDrawCard(name)
            }
        }

    companion object {
        private val YES_OR_NO = listOf("y", "n")
    }
}
