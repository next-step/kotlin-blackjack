package blackjack

object InputView {
    fun getPlayerNames(): Array<String> {
        val inputNames = readln()
        require(inputNames.isNotBlank()) { "참가자의 이름을 올바르게 입력해주세요. $inputNames" }
        return inputNames.split(",").toTypedArray()
    }
}