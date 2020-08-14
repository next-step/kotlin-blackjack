package blackjack

object ResultView {

    fun printPreview(game: BlackJackGame) {
        println()
        println("${game.dealer.name}와 ${game.players.joinToString { it.name }}에게 ${Constant.FIRST_HAVE_NUMBER_OF_CARD}장의 카드를 나누었습니다.")
        println("${game.dealer.name}: ${game.dealer.myCards.first()}")
        game.players.map(::printCard)
    }

    fun printCard(player: Gamer) {
        println("${player.name}카드: ${player.myCards.joinToString()}")
    }

    fun printResult(game: BlackJackGame) {
        println()
        if ((game.dealer as Dealer).isReceivedExtraCard()) println("딜러는 16이하라 한장의 카드를 더 받았습니다")
        println()
        println("${game.dealer.name} 카드: ${game.dealer.myCards.joinToString()} - 결과: ${game.dealer.calculatePoint()}")
        game.players.map { println("${it.name}카드: ${it.myCards.joinToString()} - 결과: ${it.calculatePoint()}") }
    }
}
