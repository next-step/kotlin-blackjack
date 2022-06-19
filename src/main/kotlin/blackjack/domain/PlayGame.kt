package blackjack.domain

class PlayGame(
    private val players: List<Player>
) {
    fun start() {
        players.map { player ->
            repeat(2) {
                if (CardDeck.isLeft()) {
                    player.receiveCard(CardDeck.getOne())
                } else {
                    throw IllegalStateException("카드가 존재하지 않습니다.")
                }
            }
        }
    }
}
