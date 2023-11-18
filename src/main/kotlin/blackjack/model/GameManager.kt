package blackjack.model

class GameManager(playerNames: List<String>) {
    val players: List<Player>

    init {
        players = playerNames.map { Player(it, CardDeck(CardDealer.getCards(CardDeck.FIRST_CARD_COUNT))) }
    }

    fun playGame() {
        players.forEach {
            PlayDealer.playGameWithEachPlayer(it)
        }
    }

    fun finishGame(): List<GameResult> = players.map { player ->
        GameResult(player, ResultDealer.getTotalScore(ResultDealer.getCardValues(player.cardDeck)))
    }
}
