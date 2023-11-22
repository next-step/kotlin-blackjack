package blackjack.domain.game

import blackjack.domain.card.*
import blackjack.domain.player.PlayerScore
import blackjack.domain.player.player
import blackjack.domain.player.playerGroup
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

val mockDealer = object : CardDealer {
    override fun selectCard(): Card {
        return Card.of(CardKind.DIAMOND, CardNumber.JACK)
    }

    override fun selectCard(count: Int): CardSet {
        return CardSet.of(
            Card.of(CardKind.DIAMOND, CardNumber.JACK), Card.of(CardKind.DIAMOND, CardNumber.QUEEN)
        )
    }
}

class BlackJackGameTest : FunSpec({
    test("블랙잭 카드 게임이 시작하면 2장의 카드를 플레이어게 지급한다") {
        val playerGroup = playerGroup {
            players(player { name("jack") }, player { name("john") })
        }
        val blackJackGame = BlackJackGame(playerGroup, mockDealer)

        val actual = blackJackGame.start()
        actual.playerGroup.players.forAll {
            it.cardSet.size shouldBe 2
        }
    }

    test("블랙잭 게임이 끝나면 누가 어떤 카드를 받았었는지, 점수가 얼마인지 계산한다.") {
        val playerGroup = playerGroup {
            players(
                player {
                    name("spring")
                    cardSet(Card(CardKind.CLOVER, CardNumber.KING), Card(CardKind.HEART, CardNumber.SIX))
                },
                player {
                    name("john")
                    cardSet(Card(CardKind.DIAMOND, CardNumber.KING), Card(CardKind.SPADE, CardNumber.SEVEN))
                },
            )
        }
        val blackJackGameResult = BlackJackGame(playerGroup).end()
        blackJackGameResult shouldBe BlackJackGameResult(
            listOf(
                PlayerScore(playerGroup[0], CardScore(16)),
                PlayerScore(playerGroup[1], CardScore(17))
            )
        )
    }
})
