package model

class CardVendor {
    fun giveCardToPlayer(players: Players, names: Names) {
        names.values.forEach {
            dealOut(players, it)
        }
    }

    private fun dealOut(players: Players, name: String) {
        repeat(DEAL_OUT) {
            hit(players, name)
        }
    }

    fun giveExtraCard(players: Players, isNeedExtraCard: (String) -> Boolean, showSpecificUserCardState: (String, List<Card>) -> Unit) {
        findUsersWhoNeedExtraCard(players).forEach {
            if (isNeedExtraCard(it.key)) {
                hit(players, it.key)
                showSpecificUserCardState(it.key, it.value)
            }
        }
    }

    private fun hit(players: Players, name: String) {
        players.updateCard(name, Deck.getCard())
    }

    private fun findUsersWhoNeedExtraCard(
        players: Players
    ): Map<String, List<Card>> {
        return players.get().filter {
            isGetExtraCard(it.value)
        }
    }

    fun isGetExtraCard(cards: List<Card>): Boolean {
        return sumCardNumbers(cards) < BLACK_JACK
    }

    fun sumCardNumbers(cards: List<Card>): Int {
        var total = 0
        cards.forEach {
            total += if (it.cardNumber.isAce()) {
                decideAceCardNumber(total)
            } else {
                it.cardNumber.number
            }
        }
        return total
    }

    fun decideAceCardNumber(totalExceptAce: Int): Int {
        val ace = when (totalExceptAce) {
            in RANGE_FOR_ACE_11 -> 11
            else -> 1
        }
        return ace
    }

    companion object {
        private const val DEAL_OUT: Int = 2
        private const val BLACK_JACK = 21
        private val RANGE_FOR_ACE_11 = 0..10
    }
}
