package blackjack

class GameController {

    fun execute(round: Round) {
        val players = inputName()
        divideCards(players)
        players.forEach {
            playerTurn(it, round)
        }
        result(players)
    }

    private fun inputName(): List<Player> {
        val names = InputParser.parse(InputView.inputNames())
        val players = mutableListOf<Player>().apply {
            addAll(names.map { Player(PlayerInfo(it)) })
        }
        return players
    }

    private fun divideCards(players: List<Player>) {
        OutputView.divideCard(players.map { it.info.name })
        OutputView.printPlayersCards(players)
    }

    private fun playerTurn(player: Player, round: Round) {
        while (player.canGetCard() && InputView.inputCard(player.info.name)) {
            player.addCard(round.getCard())
            OutputView.printCards(player)
        }
    }

    private fun result(players: List<Player>) {
        OutputView.printPlayersCards(players, isResult = true)
    }
}
