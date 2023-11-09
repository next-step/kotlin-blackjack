package blackjack.domain

class Player(val name: String, val playerCard: PlayerCard = PlayerCard()) {

    fun hit(trumpCard: TrumpCard) {
        playerCard.add(trumpCard.draw())
    }

    fun burst(): Boolean {
        return playerCard.score().isBurst()
    }
}
