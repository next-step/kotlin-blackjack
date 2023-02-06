package model

import view.ResultView

class CardVendor(private val deck: Deck) {
    fun dealOut(player: Participant) {
        repeat(DEAL_OUT) {
            hit(player)
        }
    }

    private fun hit(player: Participant) {
        player.receiveCard(deck.selectCard())
    }

    fun giveExtraCardToDealer(dealer: Dealer) {
        if (dealer.isExtraCard()) {
            hit(dealer)
            ResultView.showReceivedCardByDealer()
        }
    }

    fun giveExtraCardToPlayer(
        players: Players,
        agreeToReceiveExtraCard: (String) -> Boolean,
        showSpecificUserCardState: (Participant) -> Unit
    ) {
        players.values.forEach { player ->
            giveCard(player, agreeToReceiveExtraCard, showSpecificUserCardState)
        }
    }

    private fun giveCard(
        player: Player,
        agreeToReceiveExtraCard: (String) -> Boolean,
        showSpecificUserCardState: (Participant) -> Unit
    ) {
        while (player.isExtraCard() && agreeToReceiveExtraCard(player.name)) {
            hit(player)
            showSpecificUserCardState(player)
        }
    }

    companion object {
        private const val DEAL_OUT: Int = 2
    }
}
