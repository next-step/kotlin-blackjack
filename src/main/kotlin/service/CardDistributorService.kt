package service

import domain.CardDeck
import domain.Dealer
import domain.Player

class CardDistributorService {
    companion object {
        const val PLAYER_CARD_COUNT = 2
        const val DEALER_CARD_COUNT = 2
    }

    fun distributeCards(players: Set<Player>, dealer: Dealer) {
        // 카드 덱 초기화
        val deck = CardDeck()

        // 플레이어에게 카드 분배
        players.forEach { player ->
            repeat(PLAYER_CARD_COUNT) {
                val card = deck.drawCard()
                player.receiveCard(card)
            }
        }

        // 딜러에게 카드 분배
        repeat(DEALER_CARD_COUNT)
        {
            val card = deck.drawCard()
            dealer.receiveCard(card)
        }
    }
}