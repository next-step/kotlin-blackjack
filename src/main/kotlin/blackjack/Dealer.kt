package blackjack

import blackjack.card.Card
import blackjack.card.signature.CardOrdinalSignature
import blackjack.card.signature.CardSignaturePack
import blackjack.card.signature.CardSymbolSignature

class Dealer {
    fun provideCard(players: List<Player>, numberOfCards: Int) {
        require(numberOfCards > 0) { "나누어주려는 카드의 갯수는 0보다 커야합니다." }

        players.forEach { player ->
            repeat(numberOfCards) {
                player.addCard(castRandomCard())
            }
        }
    }

    private fun castRandomCard(): Card {
        return Card(
            CardSignaturePack(CardOrdinalSignature.pickRandomOne(), CardSymbolSignature.pickRandomOne()),
        )
    }
}
