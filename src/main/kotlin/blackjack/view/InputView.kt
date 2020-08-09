package blackjack.view

object InputView {
    fun getPlayers(): String {
        println("플레이어명을 입력해주세요.")
        val playerNames = readLine()!!
        require(playerNames.isNotEmpty())
        return playerNames
    }
}
