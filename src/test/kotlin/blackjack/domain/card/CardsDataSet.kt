package blackjack.domain.card

import blackjack.domain.player.Player

class CardsDataSet {
    companion object {
        fun testData(): Cards {
            val cardVendor = CardVendor()

            val size = (Player.INIT_CARD_COUNT..Player.INIT_CARD_COUNT+2).random()

            return Cards((1..size).map { cardVendor.drawCard() })
        }
    }
}
