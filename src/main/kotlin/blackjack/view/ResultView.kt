package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun showCardDistribution(players: List<Player>) {
        val cardDistribution = StringBuilder()
        players.forEach { cardDistribution.append(it.name).append(", ") }
        cardDistribution.delete(cardDistribution.lastIndex - 2, cardDistribution.lastIndex)
        cardDistribution.append("${players.size}장의 카드를 나누었습니다.")
        println(cardDistribution.toString())
    }

    fun showPlayerCard(player: Player) {
        val playerCard = StringBuilder()
        playerCard.append("${player.name} + 카드:")
        player.cards.forEach { "${it.value} + ${it.suit.name} + , " }
        print(playerCard)
    }

    fun showResult(players: List<Player>) {
        players.forEach {
            showPlayerCard(it)
            println(" - 결과 ${it.calculatePoint()}")
        }
    }
}
