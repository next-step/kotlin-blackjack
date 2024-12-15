package blackjack.domain

class Gambler(name: String) : Player(name) {
    override fun canNotReceiveCard(): Boolean {
        return exceedsWinScore()
    }
}
