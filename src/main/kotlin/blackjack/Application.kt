package blackjack

import blackjack.Dealer.Companion.DEALER_STANDARD_SCORE

fun main() {
    val players = Players(InputView.inputPlayers())
    OutputView.showPlayersCard(players.getPlayers())
    playGame(players.getPlayers())
    OutputView.showWinners(players.getPlayers())
    OutputView.showResult(getGameResult(players))
}

fun playGame(players: List<Gamer>) {
    val dealerScore = players.filterIsInstance<Dealer>()[0].getDealerScore()
    players.forEach {
        val score = it.cards.sumCardNumbers()
        if (score > Cards.WIN_SCORE) return
        addGamer(it)
    }
    if (dealerScore <= DEALER_STANDARD_SCORE) {
        OutputView.showDealerStatus()
    }
}

private fun addGamer(player: Gamer) {
    when (player) {
        is Player -> addUserCards(player)
        is Dealer -> selectMoreDealerCard(player)
    }
}

private fun selectMoreDealerCard(player: Dealer) {
    player.addCards(Card.getInstances())
}

private fun addUserCards(player: Player) {
    loop@ while (true) {
        try {
            val selectedValue = InputView.selectMoreCard(player.name)
            if (player.addMoreCards(selectedValue, Card.getInstances())) break@loop
            OutputView.showUserCards(player)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            break@loop
        }
    }
}

private fun getGameResult(players: Players): List<Pair<String, Result>> {
    val dealerScore = players.getPlayers().filterIsInstance<Dealer>()[0].getDealerScore()
    val playerScore = players.getPlayers().filterIsInstance<Player>()

    return playerScore.map {
        when {
            dealerScore > Cards.WIN_SCORE -> Pair(it.name, Result.WIN)
            dealerScore > it.cards.sumCardNumbers() -> Pair(it.name, Result.LOSE)
            dealerScore < it.cards.sumCardNumbers() -> Pair(it.name, Result.WIN)
            else -> Pair(it.name, Result.DRAW)
        }
    }
}
