package blackjack.domain

class PlayGame {
    private val cardDeck = CardDeck()
    fun start(player: Player) {
        repeat(2) {
            hit(player)
        }
    }

    fun hit(player: Player) {
        if (cardDeck.isLeft()) {
            player.receiveCard(cardDeck.getOne())
            return
        }
        throw IllegalStateException("카드가 존재하지 않습니다.")
    }
}
