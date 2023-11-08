package blackjack.domain

import blackjack.dsl.BlackJackDsl.initPlayerCard
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    given("플레이어 이름이 주어지면") {
        val name = "원동재"
        `when`("플레이어 생성을 하면") {
            val player = Player(name)
            then("플레이어의 이름은 원동재가 된다.") {
                player.name shouldBe "원동재"
            }
        }
    }

    given("플레이어 이름과 트럼프 카드가 주어지고") {
        val name = "원동재"
        val trumpCard = TrumpCard.init()
        `when`("플레이어 생성을 하면") {
            val player = Player(
                name,
                initPlayerCard {
                    init(trumpCard)
                }
            )
            then("플레이어의 이름은 원동재가 된다.") {
                player.name shouldBe "원동재"
            }
            then("플레이어의 카드는 2장이 된다.") {
                player.playerCard.cards.size shouldBe 2
            }
        }
    }

    given("트럼프 카드가 주어지고 플레이어가 2장을 받았다면") {
        val trumpCard = TrumpCard.init()
        val player = Player(
            "원동재",
            initPlayerCard {
                init(trumpCard)
            }
        )
        `when`("플레이어 카드에 카드를 한장 더 뽑으면") {
            player.hit(trumpCard)
            then("플레이어 카드는 3장이다.") {
                player.playerCard.cards.size shouldBe 3
            }
        }
    }

    given("플레이어가 카드 스페이드A 하트9를 받았다면") {
        val player = Player(
            "원동재",
            initPlayerCard {
                add(Card(Suit.SPADE, Rank.ACE))
                add(Card(Suit.HEART, Rank.NINE))
            }
        )
        `when`("플레이어의 점수를 계산하면") {
            val score = player.score()
            then("플레이어의 점수는 20점이다.") {
                score shouldBe 20
            }
        }
    }

    given("플레이어가 카드 스페이드A 하트9 다이아몬드 킹을 받았다면") {
        val player = Player(
            "원동재",
            initPlayerCard {
                add(Card(Suit.SPADE, Rank.ACE))
                add(Card(Suit.HEART, Rank.NINE))
                add(Card(Suit.DIAMOND, Rank.KING))
            }
        )
        `when`("플레이어의 점수를 계산하면") {
            val score = player.score()
            then("플레이어의 점수는 20점이다.") {
                score shouldBe 20
            }
        }
    }
})
