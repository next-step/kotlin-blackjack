package blackjack.domain.player

import blackjack.domain.GameMoney
import blackjack.domain.card.BlackCardDeck
import blackjack.domain.card.Card
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
                    PlayerName("pobi"),
                    GameMoney(10000)
                )
            ),
            cardSet
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
        val bettingMoney = 10000
        val players = Players(
            mutableListOf(
                Player(
                    PlayerName("pobi"),
                    GameMoney(bettingMoney)
                )
            ),
            cardSet
        )
        players.getPlayers().forEach {
            if (it is Player) {
                it.cards.isBlackJack() shouldBe true
            }
        }
        players.judgeGameResult()
        players.getPlayers().forEach {
            if (it is Player) {
                it.gameResultState shouldBe GameResultState.WIN
                it.finalIncome.money shouldBe (bettingMoney * 1.5)
            }
        }
    }
})
