package blackjack

object BlackjackGame {
    fun playGame(players: List<Player>) {
        players.forEach {
            if (it.getCards().getScore() > Cards.WIN_SCORE) return
            addUserCards(it)
        }
    }

    private fun addUserCards(player: Player) {
        loop@ while (true) {
            try {
                val selectedValue = InputView.selectMoreCard(player.getName())
                if (player.addMoreCards(selectedValue, Card.getInstances())) break@loop
                OutputView.getUserCard(player)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                break@loop
            }
        }
    }
}
