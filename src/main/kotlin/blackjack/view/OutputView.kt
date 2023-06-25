package blackjack.view

import blackjack.domain.Player

class OutputView {

    fun initGameSetting(player: List<Player>) {
        val names = player.map { it.name }.toTypedArray()
        println("$names 에게 2장의 카드를 나누었습니다.")
        checkPlayerCard(player)
    }

    private fun checkPlayerCard(player: List<Player>) {
        player.forEach {
            val message = it.cards.card.map { it.number.nameType + it.type.cardName }.toTypedArray()
            println("${it.name} 카드 $message")
        }
    }

    fun showResult(player: List<Player>) {
        player.forEach {
            val message = it.cards.card.map { it.number.nameType + it.type.cardName }.toTypedArray()
            val resultMessage = "- 결과: ${it.score}"
            println(message + resultMessage)
        }
    }
}
