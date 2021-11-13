package blackJack.domain

data class PlayerStatus(val cards: Cards, val decisionStatus: DecisionStatus = Hit()) {

    fun inquireCards() = cards

    fun getCurrentScore(): Int = cards.sumCards()

    fun ableGetACard(): Boolean = decisionStatus.isContinue()

    fun noWantReceiveCard(): PlayerStatus {
        return this.copy(cards = cards, decisionStatus = DecisionStatus.changeDecisionStatus(cards, false))
    }

    fun update(card: Card): PlayerStatus {
        val cards = cards + card
        return this.copy(cards = cards, decisionStatus = DecisionStatus.changeDecisionStatus(cards))
    }

    companion object {
        fun of(): PlayerStatus {
            return PlayerStatus(Cards(listOf()), Hit())
        }
    }
}
