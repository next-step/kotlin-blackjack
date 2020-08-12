package blackjack

fun main() {
    val players = Players(InputView.inputPlayers())
    OutputView.showPlayersCard(players.players)
    players.players.forEach {
        val selectedValue = InputView.selectMoreCard(it.name)
        it.selectValue(selectedValue)
    }
}
