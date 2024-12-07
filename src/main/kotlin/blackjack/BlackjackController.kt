package blackjack

class BlackjackController {
    fun createPlayers(playerNamesInput: String): Players {
        val playersNames = playerNamesInput.split()
        val players = Players.from(playersNames.map(::Player))
        return players
    }

    fun checkPlayerRequest(answerInput: String, player: Player): Boolean {
        if (answerInput == "n") return true
        player.takeNewCard()
        return false
    }
}