package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.holder.Hands
import blackjack.domain.holder.Player
import blackjack.domain.state.BlackJack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs

class PlayerTest : StringSpec({

    "처음 생성한 플레이어의 상태는 Hit이다." {
        //given
        val player = Player("harris")
        //when
        val playerAfterFirstTurn = player.firstTurn(setOf(Card.CLOVER_A, Card.CLOVER_4))
        //then
        playerAfterFirstTurn.name shouldBe "harris"
        playerAfterFirstTurn.hands shouldBe Hands(mutableSetOf(Card.CLOVER_A, Card.CLOVER_4))
        playerAfterFirstTurn.state.shouldBeInstanceOf<Hit>()
    }

    "firstTurn 함수로 두장의 카드를 입력았을 때 player의 상태는 Hit 임을 확인한다." {
        //given
        val player = Player("harris")
        //when
        val playerAfterFirstTurn = player.firstTurn(setOf(Card.CLOVER_A, Card.CLOVER_4))
        //then
        playerAfterFirstTurn.name shouldBe "harris"
        playerAfterFirstTurn.hands shouldBe Hands(mutableSetOf(Card.CLOVER_A, Card.CLOVER_4))
        playerAfterFirstTurn.state.shouldBeInstanceOf<Hit>()
    }

    "firstTurn 함수로 두장의 카드를 입력받았을 때 Ace, J이면 player의 상태는 BlackJack 임을 확인한다." {
        //given
        val player = Player("harris")
        //when
        val playerAfterFirstTurn = player.firstTurn(setOf(Card.CLOVER_A, Card.CLOVER_J))
        //then
        playerAfterFirstTurn.name shouldBe "harris"
        playerAfterFirstTurn.hands shouldBe Hands(mutableSetOf(Card.CLOVER_A, Card.CLOVER_J))
        playerAfterFirstTurn.state.shouldBeInstanceOf<BlackJack>()
    }

    "카드의 총 합이 21이 초과하면 bust 임을 확인한다." {
        //given
        val player = Player("harris")
        //when
        val playerAfterFirstTurn = player.firstTurn(setOf(Card.CLOVER_K, Card.CLOVER_J))
        playerAfterFirstTurn.addCard(Card.CLOVER_2)
        //then
        playerAfterFirstTurn.name shouldBe "harris"
        playerAfterFirstTurn.state.shouldBeInstanceOf<Bust>()
    }

    "addCard 함수로 5점인 카드 두개를 추가하면 카드 포인트는 10이다." {
        //given
        val harris = Player("harris")

        //when
        harris.addCard(Card.DIAMOND_5)
        harris.addCard(Card.CLOVER_5)

        //then
        harris.cardPoint() shouldBe Point(10)
    }

    "bust인 플레이어와 bust인 딜러가 있을 때 플레이어는 항상 진다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_5, Card.CLOVER_10, Card.CLOVER_K)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_9, Card.DIAMOND_3)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        assertSoftly {
            dealer.bust() shouldBe true
            harris.bust() shouldBe true
            result shouldBe -10000
        }
    }

    "19점인 플레이어와 bust인 딜러가 있을 때 플레이어는 승리한다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_5, Card.CLOVER_10, Card.CLOVER_K)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_2, Card.CLOVER_2)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        assertSoftly {
            dealer.bust() shouldBe true
            result shouldBe 10000
        }
    }

    "BlackJack인 플레이어와 21점인 딜러가 있을 때 플레이어가 flip 함수를 호출한 결과 플레이어가 BlackJack으로(상금 1.5배) 승리한다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_5, Card.DIAMOND_5, Card.CLOVER_A)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_A)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe 15000
    }

    "BlackJack인 플레이어와 BlackJack인 딜러가 있을 때 플레이어가 flip 함수를 호출한 결과 플레이어가 결과는 무승부다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_10, Card.CLOVER_A)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_A)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe 0
    }

    "BlackJack인 플레이어와 BUST 딜러가 있을 때 플레이어는 베팅한 금액의 1.5배를 받는다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_10, Card.CLOVER_9, Card.CLOVER_3)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_A)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe 15000
    }

    "21점인 플레이어와 BlackJack인 딜러가 있을 때 플레이어가 flip 함수를 호출한 결과 플레이어가 패배한다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_10, Card.CLOVER_A)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_5, Card.CLOVER_5, Card.CLOVER_A)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe -10000
    }

    "19점인 플레이어와 20점인 딜러가 있을 때 플레이어가 flip 함수를 호출한 결과 플레이어가 패배한다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_10, Card.CLOVER_J)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_9)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe -10000
    }

    "19점인 플레이어와 18점인 딜러가 있을 때 플레이어가 flip 함수를 호출한 결과 플레이어가 승리한다." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_10, Card.CLOVER_8)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_10, Card.CLOVER_9)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe 10000
    }

    "19점인 플레이어와 19점인 딜러가 있을 때 결과는 무승부이다.." {
        //given
        val dealer = Dealer(hands = Hands(mutableSetOf(Card.CLOVER_9, Card.CLOVER_10)))
        val harris = Player(
            "harris",
            hands = Hands(mutableSetOf(Card.DIAMOND_9, Card.DIAMOND_10)),
            BettingAmount(10000)
        )

        //when
        val result = harris.flip(dealer)

        //then
        result shouldBe 0
    }
})
