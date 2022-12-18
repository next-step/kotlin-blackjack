package ui

import domain.*

object OutputView {

    fun printGameStartMsg(playerNames: List<String>) {
        println("${playerNames.joinToString()} 에게 2장의 나누었습니다.")
    }

    fun printCardStatus(participators : GameParticipators) {
        participators.participators.forEach {
            this.printCardStatus(it)
            println()
        }
    }

    fun printCardStatus(participator: GameParticipator) {
        val cardViews = participator.cards.cards.map {
            cardView(it)
        }
        if (participator is Player){
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
}
