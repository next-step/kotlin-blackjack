package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.dto.StayResult

class BlackJack(private val deck: Deck) {

    fun firstPick(player: Player) {
        repeat(2) {
            hit(player)
        }
    }

    fun hit(player: Player) {
        val card = deck.draw()
        player.enroll(card)
    }

    fun stay(stayResult: StayResult): Boolean {
        return !stayResult.isStay
    }

    fun bust(player: Player): Boolean {
        return Score.isHigherThanWinScore(player)
    }

    companion object {
        const val WIN_SCORE: Int = 21
    }
}
