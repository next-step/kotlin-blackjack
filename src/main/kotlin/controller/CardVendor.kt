package controller

import model.Card
import model.Deck
import model.Person
import model.Players

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

    private fun giveExtraCardToDealer(player: Person) {
        if (isGetExtraCardForDealer(player.openCard())) {
            hit(player)
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
        }
    }

    fun giveExtraCard(
        players: Players,
        isNeedExtraCard: (String) -> Boolean,
        showSpecificUserCardState: (String, List<Card>) -> Unit
    ) {
        players.get().forEach { player ->
            while (isGetExtraCard(player.openCard()) && isNeedExtraCard(player.notifyName())) {
                hit(player)
                showSpecificUserCardState(player.notifyName(), player.openCard())
            }
        }
        giveExtraCardToDealer(players.get()[0])
    }

    fun isGetExtraCard(cards: List<Card>): Boolean {
        return CardNumberCalculator().sumCardNumbers(cards) < BLACK_JACK
    }

    fun isGetExtraCardForDealer(cards: List<Card>): Boolean {
        return CardNumberCalculator().sumCardNumbers(cards) <= DEALER_INITIAL_SUM
    }

    companion object {
        private const val DEAL_OUT: Int = 2
        private const val BLACK_JACK = 21
        private const val DEALER_INITIAL_SUM = 16
    }
}
