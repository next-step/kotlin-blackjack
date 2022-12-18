package ui

import domain.*

object OutputView {

    fun printGameStartMsg(playerNames: List<String>) {
        println("${playerNames.joinToString()} 에게 2장의 나누었습니다.")
    }

    fun printCardStatus(participators: GameParticipators) {
        participators.participators.forEach {
            this.printCardStatus(it)
            println()
        }
    }

    fun printCardStatus(participator: GameParticipator) {
        val cardViews = participator.cards.cards.map {
            cardView(it)
        }
        if (participator is Player) {
            print("${participator.name.name}카드 : ${cardViews.joinToString()}")
            return
        }
        print("딜러카드 : ${cardViews.joinToString()}")
    }

    fun printCardStatusWithResult(participators: List<GameParticipator>) {
        println()
        participators.forEach {
            printCardStatus(it)
            println("- 결과: ${it.choiceBestScore()}")
        }
    }

    private fun cardView(card: Card) = CardNumberView.valueOf(card.number) + CardShapeView.valueOf(card.shape)
    fun printWinner(winners: Map<Player, WinStatus>) {
        println("## 최종 승패")
        printDealerWinStatus(winners)
        winners.forEach { (k, v) -> println("${k.name.name} : ${WinStatusView.valueOf(v)}") }
    }

    private fun printDealerWinStatus(winners: Map<Player, WinStatus>) {
        val winCount = winners.values.count { it == WinStatus.LOSE }
        val loseCount = winners.values.count { it == WinStatus.WIN }
        val tieCount = winners.values.count { it == WinStatus.TIE }
        println("딜러 : ${winCount}승 ${loseCount}패 ${tieCount}무")
    }
}
