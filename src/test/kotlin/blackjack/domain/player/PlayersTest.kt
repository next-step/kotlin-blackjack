package blackjack.domain.player

import blackjack.domain.card.BlackCardDeck
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다" {
        val cardSet = BlackCardDeck(
            mutableSetOf(
                Card(CardNumber.CARD_ACE, CardType.CLOVER),
                Card(CardNumber.CARD_QUEEN, CardType.CLOVER),
                Card(CardNumber.CARD_ACE, CardType.HEART),
                Card(CardNumber.CARD_QUEEN, CardType.HEART)
            )
        )
        val players = Players(
            mutableListOf(
                Player(
                    PlayerName("pobi")
                )
            ),
            object : CardDeck {
                override fun getCard(): Card {
                    return cardSet.hitCard()
                }
            }
        )
        players.getPlayers().forEach {
            it.cards.isBlackJack() shouldBe true
        }
        players.judgeGameResult()
        players.getPlayers().forEach {
            if (it is Player) {
                it.gameResultState shouldBe GameResultState.DRAW
            }
        }
    }
    "플레이어가 블랙잭으로 승리인 경우 당첨금은 1.5배이다" {
        val cardSet = BlackCardDeck(
            mutableSetOf(
                Card(CardNumber.CARD_JACK, CardType.CLOVER),
                Card(CardNumber.CARD_QUEEN, CardType.CLOVER),
                Card(CardNumber.CARD_ACE, CardType.HEART),
                Card(CardNumber.CARD_QUEEN, CardType.HEART)
            )
        )
        val players = Players(
            mutableListOf(
                Player(
                    PlayerName("pobi")
                )
            ),
            object : CardDeck {
                override fun getCard(): Card {
                    return cardSet.hitCard()
                }
            }
        )
        val bettingMoney = "10000"
        players.getPlayers().forEach {
            if (it is Player) {
                it.setBettingMoney(bettingMoney)
                it.cards.isBlackJack() shouldBe true
            }
        }
        players.judgeGameResult()
        players.getPlayers().forEach {
            if (it is Player) {
                it.gameResultState shouldBe GameResultState.WIN
                it.finalIncome shouldBe (bettingMoney.toInt() * 1.5)
            }
        }
    }
})
