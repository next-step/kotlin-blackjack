package blackjack

object ResultView {
    fun showResult(players: List<Player>) {
        for (player in players) {
            val resultText = getResultText(player.playerCards.getBestScore())

            println("${player.name}카드: ${player.playerCards} - 결과: $resultText")
        }
    }

    fun showPlayerCards(player: Player) {
        println("${player.name}카드: ${player.playerCards}")
    }

    private fun getResultText(bestScore: Int): String {
        if (bestScore == CardsCompound.BUSTED) return "버스트"
        return bestScore.toString()
    }
}
