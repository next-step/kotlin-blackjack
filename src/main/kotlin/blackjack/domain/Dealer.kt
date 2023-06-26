package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import kotlin.random.Random

class Dealer {

    /**
     * 플레이어들에게 2장씩 카드를 분배함
     */
    fun dealOutCards(cardDeck: CardDeck, players: Players) {
        repeat(DEAL_OUT_CARD_AMOUNT){
            dealOutCard(cardDeck, players)
        }
    }

    /**
     * 플레이어들에게 1장씩 카드를 분배함
     */
    private fun dealOutCard(cardDeck: CardDeck, players: Players) {
        players.players.forEach {
            val peekedCard = peekCard(cardDeck)
            it.cards += peekedCard
        }
    }

    /**
     * 카드덱에서 랜덤한 카드를 1장 꺼냄
     */
    private fun peekCard(cardDeck: CardDeck): Card {
        val random = Random.Default
        val randomIndex = random.nextInt(cardDeck.cards.size)
        return cardDeck.peekCard(randomIndex)
    }

    companion object {
        const val DEAL_OUT_CARD_AMOUNT = 2
    }
}
