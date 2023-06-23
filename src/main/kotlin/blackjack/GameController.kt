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
            addAll(names.map { Player(it) })
        }
        OutputView.enterLine()
        return players
    }

    private fun divideCards(players: List<Player>) {
        OutputView.divideCard(players.map { it.name })
        players.forEach { OutputView.printCards(it) }
        OutputView.enterLine()
    }

    private fun playerTurn(player: Player, round: Round) {
        while (player.canGetCard()) {
            val result: String = InputView.inputCard(player.name)

            if (result == "y") {
                player.addCard(round.getCard())
                OutputView.printCards(player)
                continue
            } else if (result == "n") {
                OutputView.printCards(player)
                break
            }
            OutputView.printMessage("입력 값이 잘못되었습니다")
        }
    }

    private fun result(players: List<Player>) {
        OutputView.enterLine()
        players.forEach {
            OutputView.printCards(it, isResult = true)
        }
    }
}
