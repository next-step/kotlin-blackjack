package blackjack.integration

import blackjack.application.BlackJack
import blackjack.domain.CustomDeck
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination.ACE
import blackjack.domain.card.Denomination.KING
import blackjack.domain.card.Denomination.THREE
import blackjack.domain.card.Denomination.TWO
import blackjack.domain.card.Suit.CLOVER
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE
import blackjack.domain.cards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Deck
import blackjack.domain.participant.Money
import blackjack.domain.participant.Player
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import blackjack.domain.participant.BlackJack as BLACKJACK

class IntegrationTest : BehaviorSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    given("2명의 플레이어가 참여하는 블랙잭 게임이 있다") {
        val players = listOf(
            Player("1", bettingMoney = Money(1000)),
            Player("2", bettingMoney = Money(1000)),
        )
        `when`("딜러가 카드 분배 후 블랙잭인 상태라면") {
            val deck: Deck = CustomDeck(
                cards(
                    Card(TWO, CLOVER), Card(THREE, HEART),
                    Card(ACE, HEART), Card(KING, SPADE),
                    Card(ACE, DIAMOND), Card(KING, CLOVER)
                )
            )
            val dealer = Dealer(deck)
            val blackjack = BlackJack(dealer, players)
            blackjack.distribute()
            blackjack.matching()
            then("블랙잭 상태인 플레이어는 베팅 금액을 돌려받는다") {
                players.filter { it.state is BLACKJACK }
                    .forAll {
                        it.profit shouldBe Money(0)
                    }
            }
            then("블랙잭 상태가 아닌 플레이어는 베팅 금액을 잃는다") {
                players.filterNot { it.state is BLACKJACK }
                    .forAll {
                        it.profit shouldBe Money(-1000)
                    }
            }
        }
        `when`("딜러가 카드 분배 후 블랙잭 상태가 아니라면") {
            val deck: Deck = CustomDeck(
                cards(
                    Card(TWO, CLOVER), Card(THREE, HEART),
                    Card(ACE, HEART), Card(KING, SPADE),
                    Card(THREE, DIAMOND), Card(KING, CLOVER)
                )
            )
            val dealer = Dealer(deck)
            val blackjack = BlackJack(dealer, players)
            blackjack.distribute()
            blackjack.confirmBlackJackPlayer()
            then("블랙잭 상태인 플레이어는 베팅 금액의 1.5배를 받는다") {
                players.filter { it.state is BLACKJACK }
                    .forAll {
                        it.profit shouldBe Money(1500)
                    }
                dealer.profit shouldBe Money(-1500)
            }
        }
    }
})
