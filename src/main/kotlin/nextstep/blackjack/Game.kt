package nextstep.blackjack

class Game(
    val players: List<Player>,
    val cardPack: CardPack = CardPack()
) {
    init {
        players.forEach { player ->
            repeat(2) { player.hit(cardPack.draw()) }
        }
    }
}
