package blackjack

object ResultView {
    fun showCardShare(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
    }

    fun showResult(players: List<Player>) {
        for (player in players) {
            val resultText = getResultText(player.playerCards.getBestScore())
            println("${player.name}카드: ${player.playerCards.cardsToString()} - 결과: $resultText")
        }
    }

    fun showPlayerCards(player: Player) {
        println("${player.name}카드: ${player.playerCards.cardsToString()}")
    }

    private fun getResultText(bestScore: Int): String {
        if (bestScore == CardsCompound.BUSTED) return "버스트"
        return bestScore.toString()
    }

    private fun PlayerCards.cardsToString(): String {
        return cards.joinToString { it.cardToString() }
    }

    private fun PlayingCard.cardToString(): String {
        return "${number.numberName}${suit.korean}"
    }
}
