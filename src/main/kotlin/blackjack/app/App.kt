package blackjack.app

fun main() {
    val blackJackGame = BlackJackGame()
    val players = blackJackGame.getPlayers()
    blackJackGame.gameStart(players)
}
