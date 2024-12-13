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

    fun sumOfCard(): Int {
        var totalSum = 0
        var numberOfAce = 0

        for (card in ownedCards) {
            if (card.number == CardNumber.ACE) {
                numberOfAce++
                totalSum += CardNumber.ACE.score
            } else {
                totalSum += card.number.score
            }
        }

        while (numberOfAce > 0 && totalSum < 21) {
            numberOfAce--
            totalSum += 10
            if (totalSum > 21) {
                totalSum -= 10
            }
        }
        return totalSum
    }
}
