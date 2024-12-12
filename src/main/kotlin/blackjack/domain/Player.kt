package blackjack.domain

data class Player(
    val name: String,
    val ownedCards: MutableList<Card> = mutableListOf(),
    val actions: MutableList<HitCommand> = mutableListOf(),
) {
    fun receiveCard(card: Card) {
        ownedCards.add(card)
        actions.add(HitCommand.HIT)
    }

    fun stay() {
        actions.add(HitCommand.STAY)
    }

    fun hasBusted(): Boolean {
        return sumOfCard() <= 21
    }

    fun hasStayed(): Boolean {
        return actions.lastOrNull() == HitCommand.STAY
    }

    private fun sumOfCard(): Int {
        // todo: ACE 카드에 대한 계산 추가
        return ownedCards.map { it.number.score }
            .sumOf { it }
    }
}
