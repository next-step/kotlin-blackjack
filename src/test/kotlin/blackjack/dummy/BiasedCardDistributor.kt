package blackjack.dummy

import blackjack.model.CardDistributor
import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.PlayingCards
import blackjack.model.player.CardRecipient
import blackjack.model.player.Player

// 편항 카드 분배 : 특정 사용자에게 유리하게 카드 분배
class BiasedCardDistributor(potentialWinnerName: String) :
    BiasedEnvironment(potentialWinnerName), CardDistributor {

    private lateinit var tempCardSet: MutableList<Card>

    override fun resetCardSet() {
        this.tempCardSet = PlayingCards.createNew()
            .filter { it.denomination != Denomination.ACE } // 유리한 카드는 빼 놓음.
            .sortedBy { it.denomination.score } // 숫자별로 정열하여 유리한 카드를 줄 수 있게 함.
            .toMutableList()
    }

    override fun giveCardsTo(recipient: CardRecipient, count: Int) {
        val player = recipient as? Player ?: return

        when (player.cardCount) {
            in 0..1 -> { // 최초 2장 배분시, 낮은 숫자 카드 부터 배분
                repeat(count) {
                    recipient.addCard(tempCardSet.first())
                    tempCardSet.removeFirst()
                }
            }
            2 -> { // 3번째 장:  특정 플레이어에게 Ace Spade 카드 제공
                if (player.isPotentialWinner()) {
                    val aSpadeCard = "AS".parseToCard() ?: return
                    recipient.addCard(aSpadeCard)
                }
            }
            else -> { // 4번째 장 이후: 특정 플레이어가  blackJack이 되도록 배분.
                if (player.isPotentialWinner()) {
                    val needScore = player.state.scoreList.filter { it < 21 }
                        .map { 21 - it }.first { it in 2..10 }

                    val needCard = tempCardSet.find { it.denomination.score == needScore } ?: return
                    recipient.addCard(needCard)
                }
            }
        }
    }
}
