package model

class CardVendor {
    fun giveCardToPlayer(players: Players) {
        players.get().forEach {
            dealOut(it)
        }
    }

    private fun dealOut(player: Person) {
        repeat(DEAL_OUT) {
            hit(player)
        }
    }

    private fun hit(player: Person) {
        player.receiveCard(Deck.selectCard())
    }

    fun giveExtraCard(
        players: Players,
        isNeedExtraCard: (String) -> Boolean,
        showSpecificUserCardState: (String, List<Card>) -> Unit
    ) {
        var isDealerGetExtraCard = false
        players.get().forEach { player ->
            if (isGetExtraCardForDealer(player.notifyName(), player.openCard())) {
                hit(player)
                isDealerGetExtraCard = true
            }

            while (isGetExtraCard(player.openCard()) && isNeedExtraCard(player.notifyName())) {
                hit(player)
                showSpecificUserCardState(player.notifyName(), player.openCard())
            }
        }
        if (isDealerGetExtraCard) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
            isDealerGetExtraCard = false
        }
    }

    fun isGetExtraCard(cards: List<Card>): Boolean {
        return sumCardNumbers(cards) < BLACK_JACK
    }

    fun isGetExtraCardForDealer(name: String, cards: List<Card>): Boolean {
        return name == "딜러" && sumCardNumbers(cards) <= DEALER_INITIAL_SUM
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
        private const val DEALER_INITIAL_SUM = 16
        private val RANGE_FOR_ACE_11 = 0..10
    }
}
