package blackjack.domain

import blackjack.domain.card.Deck

object BlackJack {

    const val WIN_SCORE: Int = 21

    fun firstPick(player: Player) {
        repeat(2) {
            hit(player)
        }
    }

    fun hit(player: Player) {
        val card = Deck.draw()
        player.enroll(card)
    }

    fun stay(stayResult: StayResult): Boolean {
        return !stayResult.isStay
    }

    fun bust(player: Player): Boolean {
        return Score.isHigherThanWinScore(player)
    }
}
