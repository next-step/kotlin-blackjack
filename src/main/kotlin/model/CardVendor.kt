package model

class CardVendor {
    fun giveCardToPlayer(players: Players) {
        players.get().forEach {
            dealOut(it)
        }
    }

    private fun dealOut(player: Player) {
        repeat(DEAL_OUT) {
            hit(player)
        }
    }

    private fun hit(player: Player) {
        player.addCard(Deck.selectCard())
    }

    fun giveExtraCard(
        players: Players,
        isNeedExtraCard: (String) -> Boolean,
        showSpecificUserCardState: (String, List<Card>) -> Unit
    ) {
        players.get().forEach { player ->
            if (isGetExtraCard(player.getCard())) {
                if (isNeedExtraCard(player.getName())) {
                    hit(player)
                    showSpecificUserCardState(player.getName(), player.getCard())
                }
            }
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
