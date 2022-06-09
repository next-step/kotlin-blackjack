import domain.blackJack.BlackJack
import domain.card.CardDeck
import domain.player.Player
import domain.player.Players
import view.InputView
import view.OutputView

fun main() {
    val playerNames = InputView.inputPlayerName()
    val players = Players(playerNames.map { Player(it) })
    val blackJack = BlackJack(players, CardDeck())

    blackJack.start()
    OutputView.firstDrawTo(players)
    repeat(players.size()) { OutputView.printHandsOf(players[it]) }
    repeat(players.size()) { playerRace(blackJack, players[it]) }
    OutputView.gameResult(blackJack.players)
}

fun playerRace(blackJack: BlackJack, player: Player) {
    while (player.canDraw() && InputView.askToDraw(player)) {
        blackJack.progress(player)
    }

    OutputView.printHandsOf(player)
}