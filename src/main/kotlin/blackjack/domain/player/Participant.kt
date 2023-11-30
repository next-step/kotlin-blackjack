package blackjack.domain.player

import blackjack.domain.GameResult
import blackjack.domain.card.Deck
import blackjack.domain.rule.DefaultScoringRule
import blackjack.domain.rule.ScoringRule
import blackjack.domain.rule.State

class Participant(
    override val name: String,
    val bet: Int,
    private val scoringRule: ScoringRule
) : Player(name, scoringRule) {

    init {
        require(name.length <= 10) { "이름은 10자를 넘을 수 없습니다." }
    }

    fun nextTurn(state: State, deck: Deck) {
        if (canDraw().not()) return

        when (state) {
            State.HIT -> hit(deck)
            State.STAY -> stay()
            else -> throw IllegalArgumentException("참가자의 다음 턴 상태는 HIT 또는 STAY만 가능합니다.")
        }
    }

    private fun hit(deck: Deck) {
        state = State.HIT
        draw(deck)

        if (canDraw().not()) state = State.BUST
    }

    private fun stay() {
        state = State.STAY
    }

    override fun draw(deck: Deck) {
        this._cards.add(deck.draw())
    }

    override fun canDraw(): Boolean {
        return this.state.isFinished.not()
                && scoringRule.isOverThreshold(totalScore, DefaultScoringRule.THRESHOLD_SCORE).not()
    }

    fun compareWith(dealer: Dealer): GameResult {
        val dealerGameResult = dealer.compareWith(this)
        val result = when (dealerGameResult) {
            GameResult.WIN -> GameResult.LOSE
            GameResult.LOSE -> GameResult.WIN
            GameResult.TIE -> GameResult.TIE
        }

        return result
    }
}