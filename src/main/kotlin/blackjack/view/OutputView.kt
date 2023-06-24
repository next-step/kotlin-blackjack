package blackjack.view

object OutputView {
    fun roundBeginNotice(statuses: List<PlayerStatus>) {
        val dealerStatus = statuses.find { it.name == "딜러" }
        check(dealerStatus != null) { "딜러는 비어있을 수 없습니다." }
        val playerNames = statuses.filter { it.name != "딜러" }.joinToString { it.name }
        println("\n${dealerStatus.name}와 ${playerNames}에게 2장의 나누었습니다.")
        statuses.forEach(::handNotice)
        println()
    }

    fun handNotice(status: PlayerStatus) {
        println("${status.name}카드: ${status.handRepresent}")
    }

    fun roundResultNotice(players: List<PlayerStatus>) {
        println()
        players.forEach {
            println("${it.name}카드: ${it.handRepresent} - 결과: ${it.optimalValue}")
        }
    }

    fun dealerAddNotice() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
