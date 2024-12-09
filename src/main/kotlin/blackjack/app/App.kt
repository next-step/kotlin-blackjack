package blackjack.app

fun main() {
    val blackJackGame = BlackJackGame()
    val players = blackJackGame.getPlayers()
    blackJackGame.gameStart(players)
    blackJackGame.playTurn(players)
    blackJackGame.finishGame(players)
}
