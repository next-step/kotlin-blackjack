import domain.card.Deck
import domain.player.Player
import domain.player.Players
import view.Answer
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val playerNames = inputView.getPlayerNames()
    val deck = Deck()
    val players = initGame(playerNames = playerNames, deck = deck)

    resultView.printInitPlayers(players = players)

    players.forEach {
        gameStart(player = it, deck = deck, inputView = inputView, resultView = resultView)
    }

    resultView.printGameResult(players = players)
}

private fun initGame(playerNames: List<String>, deck: Deck): Players =
    Players(
        playerNames.map {
            Player(it, deck.issueCard(), deck.issueCard())
        },
    )

private fun gameStart(
    player: Player,
    inputView: InputView,
    deck: Deck,
    resultView: ResultView
) {
    while (true) {
        val answer = inputView.askDraw(player.name)
        if (Answer.YES != answer) {
            return
        }

        if (!player.state.isDrawable()) {
            resultView.printCannotProceed(player)
            return
        }

        player.draw(deck.issueCard())
        resultView.printPlayerCards(player)
    }
}
