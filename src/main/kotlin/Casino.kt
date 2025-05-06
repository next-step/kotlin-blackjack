import view.InputView
import view.OutputView

class Casino(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val deck = Deck(PlayingCard.ALL.shuffled())
        val names = inputView.getPlayerNames()
        val players = names.map { Player(it, Hand(deck.drawCard(2))) }
        outputView.printFirstTurn(players)

        players.forEach { turn(it, deck) }
        players.forEach { outputView.printScore(it) }
    }

    private fun turn(
        player: Player,
        deck: Deck,
    ) {
        while (true) {
            val response = inputView.getResponse(player.name)
            if (!response || player.hand.isBust()) return
            player.drawCards(deck.drawCard(1))
            outputView.printPlayerCards(player)
        }
    }
}
