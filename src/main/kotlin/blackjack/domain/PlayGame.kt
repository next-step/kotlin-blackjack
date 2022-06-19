package blackjack.domain

class PlayGame {
    fun start(player: Player) {
        repeat(2) {
            hit(player)
        }
    }

    fun hit(player: Player) {
        if (CardDeck.isLeft()) {
            player.receiveCard(CardDeck.getOne())
        } else {
            throw IllegalStateException("카드가 존재하지 않습니다.")
        }
    }
}
