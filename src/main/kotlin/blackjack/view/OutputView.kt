package blackjack.view

import blackjack.domain.Player

class OutputView {

    fun initGameSetting(player: List<Player>) {
        val names = player.map { it.name }.toTypedArray()
        println("${names.contentToString()} 에게 2장의 카드를 나누었습니다.")
        player.forEach {
            checkPlayerCard(it)
        }
    }

    fun checkPlayerCard(player: Player) {
        val message = player.cards.card.map {
            it.number.nameType + it.type.cardName
        }.toTypedArray()
        println("${player.name} 카드 ${message.contentToString()}")
        println()
    }

    fun showResult(player: List<Player>) {
        player.forEach {
            val message = it.cards.card.map { it.number.nameType + it.type.cardName }.toTypedArray()
            val resultMessage = "- 결과: ${it.score}"
            println(message.contentToString() + resultMessage)
        }
    }
}
