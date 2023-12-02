package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.rule.MatchedProfitRule
import blackjack.domain.rule.ScoringRule

class Participant(
    override val name: String,
    val bet: Int,
    scoringRule: ScoringRule
) : Player(name, scoringRule) {

    init {
        require(name.length <= 10) { "이름은 10자를 넘을 수 없습니다." }
    }

    fun stay() {
        this.cards.stay()
    }

    fun profit(dealer: Dealer, matchedProfitRule: MatchedProfitRule) : Int {
        return matchedProfitRule.profit(dealer.cards, this.cards, bet)
    }

    override fun draw(deck: Deck) {
        this.cards.add(deck.draw())
    }
}
