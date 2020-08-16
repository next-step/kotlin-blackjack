package blackjack

fun main() {
    val players = Players(InputView.inputPlayers())
    OutputView.showPlayersCard(players.getPlayers())
    BlackjackGame.playGame(players.getPlayers())
    OutputView.getWinner(players.getPlayers())
}
