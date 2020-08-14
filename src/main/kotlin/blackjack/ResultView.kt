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
}
