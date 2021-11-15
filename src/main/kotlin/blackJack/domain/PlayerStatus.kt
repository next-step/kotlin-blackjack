package blackJack.domain

data class PlayerStatus(val cards: Cards, val decisionStatus: PlayerDecision = Hit()) {

    fun sumScore(): Int = cards.sumCards()

    fun ableGetACard(): Boolean = decisionStatus.isContinue()

    fun changeContinueStatus(boolean: Boolean): PlayerStatus {
        return this.copy(cards = cards, decisionStatus = PlayerDecision.changeDecision(cards, boolean))
    }

    fun update(card: Card): PlayerStatus {
        val cards = cards + card
        return this.copy(cards = cards, decisionStatus = PlayerDecision.changeDecision(cards))
    }

    fun isBlackJack(): Boolean = decisionStatus is BlackJack

    companion object {
        fun of(): PlayerStatus {
            return PlayerStatus(Cards(listOf()), Hit())
        }
    }
}
