package blackjack

import blackjack.state.PlayerState
import blackjack.state.Start

class Player(
    val name: String,
    private var hand: Cards = Cards(),
    private var state: PlayerState = Start,
) {
    fun addCardToHand(card: Card) {
        this.hand = this.hand.add(card)
        if (hand.totalScore().isBust()) {
            this.bust()
            return
        }
        if (hand.totalScore().isBlackjack()) {
            this.blackjack()
        }
    }

    fun handSize(): Int {
        return this.hand.size()
    }

    fun totalScoreInHand(): Score {
        return this.hand.totalScore()
    }

    fun isPlaying(): Boolean = this.state.isPlaying()
    fun isFinished(): Boolean = this.state.isFinished()
    fun isStart(): Boolean = this.state.isStart()
    fun isHit(): Boolean = this.state.isHit()
    fun isStay(): Boolean = this.state.isStay()
    fun isBust(): Boolean = this.state.isBust()
    fun isBlackjack(): Boolean = this.state.isBlackjack()

    fun hit() {
        if (hand.totalScore().isBlackjack() || hand.totalScore().isBust()) {
            throw IllegalStateException("손패의 총 합이 21이상이면 hit할 수 없습니다.")
        }
        this.state = this.state.hit()
    }

    fun stay() {
        if (hand.totalScore().isLessThan(Score(20))) {
            this.state = this.state.stay()
            return
        }
        throw IllegalStateException("손패 총합이 21을 이상이면 stay 상태가 될 수 없습니다.")
    }

    fun bust() {
        if (hand.totalScore().isBust()) {
            this.state = this.state.bust()
            return
        }
        throw IllegalStateException("손패 총합이 22 이상이 아니므로 Bust가 아닙니다.")
    }

    fun blackjack() {
        if (hand.totalScore().isBlackjack()) {
            this.state = this.state.blackjack()
            return
        }
        throw IllegalStateException("손패 총합이 21이 아니므로 Blackjack이 아닙니다.")
    }

    fun handToList(): List<Card> {
        return this.hand.toList()
    }
}
